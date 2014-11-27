program z20(input, output);
var x, y, Result: integer;

procedure swap(x, y: integer);
var tmp: integer;
begin
  tmp := x;
  x := y;
  y := tmp
end;

procedure gcd(x, y, result: integer);
var tanaka: integer;
begin
   if x < y then
    begin smap(x, y) end;
   if y = 0 then 
   begin result := x end
   else begin
    tanaka := x mod y;
    gcd(y, tanaka, result)
   end
end;
   
begin
  writeln('Input x');
  readln(x);
  if x >= 0 then begin
    writeln('Input y');
    readln(y);
    if y >= 0 then
      begin
        gcd(x, y, Result);
        writeln('gcd(', x, ', ', y, ') = ', Result)
      end
    else begin
       writeln('Input error')
    end
  end 
  else begin
    writeln('Input error')
  end
end.
