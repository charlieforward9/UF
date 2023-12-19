library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_arith.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity CP is
	Port ( 
		CLK, RESET_L: in std_logic;
		MACH:         in std_logic_vector(4 downto 0);
		START, COUT:  in std_logic;
		OP:           out std_logic_vector(2 downto 0);
		SEL1, SEL2:   out std_logic_vector(1 downto 0);
		CIN:		     out std_logic;
		DONE:         out std_logic
		
	);
end CP;
 
architecture arc of CP is
	 --Make code more readable with constants
    constant SEL_A	: std_logic_vector(1 downto 0) := "00";
	 constant SEL_B	: std_logic_vector(1 downto 0) := "11";
	 constant SEL_IN	: std_logic_vector(1 downto 0) := "01";
	 constant SEL_OUT	: std_logic_vector(1 downto 0) := "10";
	 
	 type StateType is (SDecode, SLoad, STab, SAnd, SOR, SAdd, SSub, SSRL, SOut); 
    signal cur_state : StateType := SDecode; -- Set the initial state
	
    signal instr : std_logic_vector(2 downto 0);
    signal dest  : std_logic_vector(1 downto 0);
	 signal temp  : std_logic_vector(1 downto 0);
	 signal BUS_I : std_logic_vector(3 downto 0);
	 signal BUS_O : std_logic_vector(3 downto 0); 

begin
    -- Split up the incoming machine code into the instruction and destination fields.
    instr <= mach(4 downto 2);
    dest <= mach(1 downto 0);

	ASM: process(CLK, RESET_L) begin
		if (RESET_L = '1') then -- Check for the RESET_L signal
			cur_state <= SDecode; -- Reset the controller
			DONE <= '0'; -- Reset DONE because it is controlled in this process
		elsif (rising_edge(CLK)) then -- Check for the rising edge
			case cur_state is
                when SDecode =>
						if (START = '1' AND dest(1) /= '1') then -- Check for START and no malformed instruction
							case instr is
								when "000" => cur_state <= SLoad;
								when "001" => cur_state <= STab;
								when "010" => cur_state <= SAnd;
								when "011" => cur_state <= SOr;
								when "100" => cur_state <= SAdd;
								when "101" => cur_state <= SSub;
								when "110" => cur_state <= SSrl;
								when "111" => cur_state <= SOut;
							end case;
							DONE <= '0'; -- Need to set DONE on our way to execution
						else
							DONE <= '0'; -- Reset DONE back to false because of malformed instruction.
                  end if;
                when SSub => -- Any multicycle instructions may need a non-default transition to the next stage
							cur_state <= SAdd;
					 when others => -- This is the default case for the rest not specified
						cur_state <= SDecode;
						DONE <= '1';  -- Because DONE depends on the transition, we have to assign it in this process
			end case;
		end if;
	end process;

	
	
	
Control: process(cur_state, dest) begin
		if (rising_edge(CLK)) then
			if (cur_state = SDecode) then
				SEL1 <= SEL_A;
				SEL2 <= SEL_B;
			elsif (cur_state = SLoad) then
				case dest is
					when "00" => -- Pointing to register A
						SEL1 <= SEL_IN; 
						SEL2 <= SEL_B; 
					when "01" => -- Pointing to register B
						SEL1 <= SEL_A;
						SEL2 <= SEL_IN;
					when others => NULL;
				end case;
			else -- Most states have the same DEST rules, so that is this else statement.
				case(dest) is
					when "00" =>
						SEL1 <= SEL_OUT;
						SEL2 <= SEL_B;
					when "01" =>
						SEL1 <= SEL_A;
						SEL2 <= SEL_OUT;
					when others => NULL;
				end case;
			end if;
			
			-- Setting OP, Cin, etc
			case cur_state is
				when STab =>
					if (dest = "00") then OP <= "101";
					else OP <= "100";
					end if;
				when SAnd =>
					OP <= "001";
				when SOR =>
					OP <= "010";
				when SAdd =>
					OP <= "011";
					CIN <= COUT;
				when SSub =>
					--temp <= dest;
					--dest <= "00";
					OP <= "000";	--Flip register A
				when SSrl =>
					OP <= "111";
				when SOut =>
					if (dest = "00") then OP <= "100";
					else OP <= "101";
					end if;
				when others => 
					if (dest = "00") then OP <= "100";
					else OP <= "101";
					end if; --OP behaves erroneous so set to whichever register dest is set to
			end case;
		end if;
	end process;
end architecture;

