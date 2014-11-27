{ program for constructing a magic square }

program pas142(output);
var     x : integer;    { index of column }
        y : integer;    { index of line }
        a : integer;    { number of line and column }
        i : integer;
        n : integer;
        max : integer;
        s : integer;
        sum : integer;
begin
        a := 5;
        max := 25;
        n := 1;
        x := (a + 1) div 2;
        y := x + 1 - a;
        while n <= max do begin
                i := 1;
                while i <= a do begin
                        if x < 1 then
                           begin x := x + a end
                        else begin
                          if x > a then
                           begin x := x - a end
                        end;

                        if y < 1 then
                           begin y := y + a end
                        else begin
                          if y > a then
                            begin y := y - a end
                        end;

                        s[x + (y - 1) * a] := n;
                        x := x - 1;
                        y := y + 1;
                        i := i + 1;
                        n := n + 1
                        end;
                x := x + 1;
                y := y + 1
                end;
        i := 0;
        writeln( '               **** magic square ****');
        while i < a do begin
                writeln('      +-------+-------+-------+-------+-------+');
                writeln('      |       |       |       |       |       |');
                writeln('      |   ', s[1+i*a], '      |   ', s[2+i*a], '      |   ', s[3+i*a],
                      ' |   ', s[4+i*a], '      |   ', s[5+i*a], '      |');
                writeln('      |       |       |       |       |       |');
                i := i + 1
                end;
        writeln('      +-------+-------+-------+-------+-------+');
        sum := (1 + max) * max div (2 * a);
        writeln('      sum of line , column or cross = ', sum)
end.
