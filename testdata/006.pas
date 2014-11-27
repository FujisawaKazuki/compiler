{       test program : NO.1             }
{               programmed by M.FUJIOKA  }
program pas132(output);
var     FACTOR : array [1..30] of integer;
                { keep prime number     }
        TIMES  : array [1..30] of integer;
                { prime number counter  }
        DATA   : array [1..10] of integer;
                { anarized data         }
        NUM    : integer;
        i,j,k  : integer;
        m,n    : integer;       { in order to make random numbers }
begin
m := 17;
n := 23;
DATA[1] := 345;
i := 2;
while i <= 10 do
        begin
        { make random number    }
        DATA[i] := DATA[i-1] * m + n;
        while DATA[i] > 1000 do
                DATA[i] := DATA[i] - 1000;
        i := i + 1
        end;
k := 1;
while k <= 10 do
        begin
        { analize number }
        j := 2;
        i := 0;
        NUM := DATA[k];
        while NUM <> 1 do
              begin
                if NUM div j*j = NUM then
                        begin
                        { define new prime number       }
                        i := i + 1;
                        FACTOR[i] := j;
                        TIMES[i] := 1;
                        NUM := NUM div j
                        end;
                while NUM div j*j = NUM do
                        begin
                        { count prime number    }
                        TIMES[i] := TIMES[i] + 1;
                        NUM := NUM div j
                        end;
                if j = 2 then begin j := 3 end
                        else begin j := j + 2 end
                end;
        j := 1;
        { display anarise number        }
        writeln(' ANARISE ',DATA[k],' = ');
        while j <= i do
                begin
                writeln('               ',FACTOR[j],'^',TIMES[j]);
                j := j + 1
                end;
        k := k + 1
        end
end.
