program z20(input, output);
var x, y, Result: integer;

procedure gcd(x, y: integer);
var nextY, tmp: integer;
begin
   if x < y then
   begin
      tmp := x;
      x := y;
      y := tmp
   end;
   if y = 0 then 
   begin Result := x end
   else begin
    nextY := x mod y;
    gcd(y, nextY)
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
        gcd(x, y);
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
