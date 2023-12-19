--ADDRESS ISSUE
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_arith.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity DP is
	Port ( 
		CLK, RESET_L, CIN:  in  std_logic;
		BUS_IN      	  :  in  std_logic_vector(3 downto 0);
		SEL1,SEL2   	  :  in  std_logic_vector(1 downto 0); 
		OPER         	  :  in  std_logic_vector(2 downto 0);
      BUS_OUT     	  :  out std_logic_vector(3 downto 0); 
		COUT		   	  :  out std_logic
	);
end DP;
 
architecture arch of DP is
	--Instantiate ALU in the Datapath
	component ALU port(
		A,B 	  	: in  std_logic_vector(3 downto 0);
		carryin 	: in  std_logic;
		OP  	  	: in  std_logic_vector(2 downto 0);
		carryout	: out std_logic;
		F   	  	: out std_logic_vector(3 downto 0));
	end component;

	signal mux1, mux2			  : std_logic_vector(3 downto 0);
	signal regA, regB, output : std_logic_vector(3 downto 0) := (others => '0'); --Initialize A and B to zero in case the mux pulls them	

begin
	-- Create the portmap for the datapath.
	a1: ALU port map(regA, regB, CIN, OPER, COUT, output);
	
	-- Process for determining the value of REGA on the clock edge
	A: process(CLK, RESET_L)
	begin
		if (RESET_L = '1') then -- Check for reset
			output <= "0000";
		elsif (rising_edge(CLK)) then -- Check for rising edge
			-- This case statement here represents MUX1
			case SEL1 is
				-- Use the numbers above MUX1 in Figure-2 to determine what regA should become
				when "00" => 
					regA <= regA; 
				when "01" => 
					regA <= BUS_IN;
				when "10" =>
					regA <= output;
				when "11" =>
					regA <= regB;
				when others => NULL; -- A NULL statement is a no-op.
			end case;
		end if;
	end process;
	
	B: process(CLK, RESET_L)
	begin
		if (RESET_L = '1') then -- Check for reset
			output <= "0000";
		elsif (rising_edge(CLK)) then
			case SEL2 is
				when "00" => 
					regB <= regA; 
				when "01" => 
					regB <= BUS_IN;
				when "10" =>
					regB <= output;
				when "11" =>
					regB <= regB;
				when others => NULL; -- A NULL statement is a no-op.
			end case;
		end if;
	end process;
	
	BUS_OUT <= output;
	
end architecture;
