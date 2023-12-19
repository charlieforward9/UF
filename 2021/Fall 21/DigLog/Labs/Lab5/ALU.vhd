library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee. math_real.round;
use IEEE.std_logic_unsigned.all;

entity ALU is port (
		A,B		: in  std_logic_vector(3 downto 0);
		carryin	: in  std_logic;
		OP 		: in  std_logic_vector(2 downto 0);
		carryout	:out std_logic:= '0';
		F  		: out std_logic_vector(3 downto 0));
end ALU;

architecture ALU_OPS of ALU is 

signal temp: std_logic_vector(4 downto 0);

begin process(OP, A, B, carryin) 
	begin
		case OP is
			when "000" => 														--2's complement
				F <= std_logic_vector(unsigned((NOT A) + 1));         
			when "001" =>                                         --Logical AND
				F <= A AND B;                                         
			when "010" =>                                         --Logical OR
				F <= A OR B;                                          
			when "011" =>                                         --Binary Addition
				F <= A + B + carryin;                                     
				temp <= '0' & A + B + carryin;                               
				carryout <= temp(4);                                   
			when "100" =>                                         --Output A
				F <= A;                                               
			when "101" =>                                         --Output B
				F <= B;                                               
			when "110" =>														--Logical Shift Left
				F <= A(2 downto 0) & '0';                             
			when "111" =>                                         --Logical Shift Right
				F <= '0' & A(3 downto 1);
			when others => NULL; -- A NULL statement is a no-op.
		end case;
	end process;
end ALU_OPS;

