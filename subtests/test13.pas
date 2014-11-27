{modification of 104.pas}

program pas104(output);

var     n:integer;
        max: integer;   { const for the number of data }
        x:integer;
        y:integer;
        sx:integer;
        sy:integer;
        sxy:integer;
        sx2:integer;
        w:integer;
        a:integer;
        b:integer;
        dx:array [1..8] of integer;
        dy:array [1..8] of integer;

begin
        max := 8;
        dx:=96;   dy[1]:=86;
        dx[2]:=89;   dy[2]:=56;
        dx[3]:=78;   dy[3]:=81;
        dx[4]:=68;   dy[4]:=86;
        dx[5]:=58;   dy[5]:=78;
        dx[6]:=49;   dy[6]:=56;
        dx[7]:=39;   dy[7]:=23;
        dx[8]:=32;   dy[8]:=24;

        n:=1;sx:=0;sy:=0;sxy:=0;sx2:=0;
        writeln('         No.         x           y          xy          x^2');
        while n<=max do begin
                writeln( n, dx[n], dy[n], dx[n]*dy[n], dx[n]*dx[n]);
                sx:=sx+dx[n];   sy:=sy+dy[n];
                sxy:=sxy+dx[n]*dy[n];
                sx2:=sx2+dx[n]*dx[n];
                n:=n+1
        end;
        writeln;
        writeln( 'Sigma(x)=   ',sx);
        writeln( 'Sigma(y)=   ',sy);
        writeln( 'Sigma(xy)=  ',sxy);
        writeln( 'Sigma(x^2)= ',sx2);
        w:=max*sx2-sx*sx;
        if w*sx2=0 then begin
                writeln( 'Fitting Unsuccessful.')
	end
	else begin
                b:=(sx2*sy - sxy*sx) div w;
                a:=(sxy-b*sx) div sx2;
                writeln;
                writeln( 'a=',a,'   b=',b)
        end
end.
