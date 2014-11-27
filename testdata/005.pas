program pas123(output);
var i, u, v : integer;
    n : integer;
    min, max : integer;
    a : array[1..20] of integer;
begin
    n := 20;
    a[1] := 35; a[2] := 68; a[3] := 94; a[4] := 7; a[5] := 88;
    a[6] := -5; a[7] := -3; a[8] := 12; a[9] := 35; a[10] := 9;
    a[11] := -6; a[12] := 3; a[13] := 0; a[14] := -2; a[15] := 74;
    a[16] := 88; a[17] := 52; a[18] := 43; a[19] := 5; a[20] := 4;
    min := a[1]; max := min; i := 2;
    while i < n do
    begin  u := a[i]; v := a[i+1];
        if u > v then
	begin  if u > max then begin max := u end;
               if v < min then begin min := v end
        end else
	begin  if v > max then begin max := v end;
               if u < min then begin min := u end
        end;
        i := i + 2
    end;
    if i = n then
    begin
       if a[n] > max then begin max := a[n] end
       else begin
	  if a[n] < min then begin min := a[n] end
       end;
       writeln('MAX = ',max,' , MIN = ',min)
    end
end.
