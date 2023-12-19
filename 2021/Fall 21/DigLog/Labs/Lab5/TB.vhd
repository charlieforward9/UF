library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_arith.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity TB is port ( 
		tCLK, tRESET_L, tSTART		  : in std_logic;
		MACHINE							  : in std_logic_vector(8 downto 0);
		tDONE: 	  					 		 out std_logic;
		tBUS_OUT: 							 out std_logic_vector(3 downto 0);
	);
end TB;

architecture TBA of TB is 
	--Instantiate the Controller Component
	component CP port ( 
		CLK, RESET_L, START, COUT: in std_logic;
		MACH							 : in std_logic_vector(4 downto 0);
		OP								 : out std_logic_vector(2 downto 0);
		SEL1, SEL2					 : out std_logic_vector(1 downto 0);
		CIN, DONE					 : out std_logic
	);
	end component;
	
	--Instantiate the Datapath Component
	component DP port ( 
		CLK, RESET_L, CIN	:  in  std_logic;
		BUS_IN      	  	:  in  std_logic_vector(3 downto 0);
		OPER         	  	:  in  std_logic_vector(2 downto 0);
		SEL1,SEL2   	  	:  in  std_logic_vector(1 downto 0); 
      BUS_OUT     	  	:  out std_logic_vector(3 downto 0); 
		COUT		   	  	:  out std_logic
	);
	end component;
 
	--Establish  signals connecting the Controller to the Datapath
	signal carryToDP, carryToCP : std_logic;
	signal operation 			 	 : std_logic_vector(2 downto 0);
	signal select1, select2  	 : std_logic_vector(1 downto 0);
	
	--Establish the signals that are derived from the MACHINE input
	signal inst						: std_logic_vector(2 downto 0);
	signal operand					: std_logic_vector(3 downto 0);
	signal dest 					: std_logic_vector(1 downto 0);
	signal controlCode			: std_logic_vector(4 downto 0);

begin
	--Get the control code from the MACHINE input
	inst <= MACHINE(8 downto 6); 
	dest <= MACHINE(1 downto 0);
	controlCode <= inst & dest;
	
	--Get the operand from the MACHINE input
	operand <= MACHINE(5 downto 2);

	--Create instances of the controller and the datapath and connect them
	c1: CP port map(tCLK, tRESET_L, tSTART, carryToCP, controlCode, operation, select1, select2, carryToDP, tDONE);
	d1: DP port map(tCLK, tRESET_L, carryToDP, operand, operation, select1, select2, tBUS_OUT, carryToCP);
	

end architecture;
