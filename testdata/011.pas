{ zzcomp.pas  910114 }
program z9(output);
var i, j, k, l, m, n			  : integer;
   ca,cb,cc, cd				  : char;
   ba, bb, bt, bf			  : boolean;
   str					  : array[-2..10]of char;
   a0, a1, a2, a3, a4, a5, a6, a7, a8, a9 : integer;

begin
   i := 6;  j := -7;  k := -8;
   ca := 'A';  cb := 'b';
   bt:=true;  bf:=false;

   l :=i;  l :=l - 6;
   m := 8+j;
   n :=3+i+2-j+k-8;
   a0 := l;  a1 := m;  a2 :=n;

   if j < i then begin l :=3 end;
   if k <= i - 7 then begin m :=4 end;
   if -k-2=6 then begin n:=5 end;
   a3 := l;  a4 := m;  a5 :=n;

   if (i>j) then begin l:=6 end;
   if i>=k+7 then begin m:=7 end;
   if -k+2<>5 then begin n:=8 end;
   a6 := l;  a7 := m;  a8 := n;

   if -i*j+j*1+(-2)*i-((-i div 3 -j/7+(-k)mod(-((1+(2+(k+i)))-2)))) <> 24
     then begin
	l:=0
     end
     else begin
	l:=9
     end;
   a9 := l;
   writeln(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);


   ba := j < i;
   if ba and (k<0) then begin l :=0 end;
   if ba = (k<0) then begin m :=1 end;
   if true <=ba then begin n :=2 end;
   a0 := l;  a1 := m;  a2 :=n;

   if bf < bt then begin l :=3 end;
   if not(not(ba and bt)or bf) then begin m:=4 end;
   if(true or false)and(true<>false) then begin n:=5 end;
   a3 := l;  a4 := m;  a5 :=n;

   if ca <= cb then begin l:=6 end;
   if ca = 'A' then begin m:=7 end;
   if not (cb<>'b') then begin n:=8 end;
   a6 := l;  a7 := m;  a8 := n;
   writeln(a0, a1, a2, a3, a4, a5, a6, a7, a8, '9');

   n :=-2;
   while n<=10 do
   begin
      str[n]:=cb;
      n:=n+1
   end;

   n:=-2;

   while n<=10 do
   begin
      if n <2 then
      begin
	 if n <0 then
	 begin str[n]:=ca end
	 else begin
	    str[n]:='B'
	 end
      end
      else begin
	 str[n]:='C'
      end;

      cd:='D';

      if (n>=4) then
      begin
	 if n <8 then
	 begin
	    if n <6 then
	    begin str[n]:=cd end
	    else
	    begin  str[n]:='E' end
	 end
	 else
	 begin
	    if n <=9 then
	    begin str[n]:='f' end
	    else
	    begin str[n]:='g' end
	 end
      end;
      n:=n+1
   end;

   writeln('AABBCCDDEEffg');
   writeln(str)
end.
