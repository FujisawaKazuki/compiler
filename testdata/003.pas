program test(input,output) ;
var	i   : integer ;
	a   : array[1..10] of integer ;

procedure printData ;
var	i : integer ;
begin
	i := 1 ;
	while i <= 10 do
	begin
		writeln(a[i]) ;
		i := i + 1
	end
end ;

begin
	i := 0 ;
	while i < 10 do
	begin
		i := i + 1 ;
		a[i] := i*i
	end ;
	printData
end.
