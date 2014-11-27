{ TO GET SUM, MAX, MIN, MEAN ,AND VARIANCE OF A GIVEN DATA,             }
{ AND TO SORT THE DATA WITH BUBBLESORT                                  }

program coverage(output);
var     Sum, Max, Min, Mean, V   : integer;
        A                        : array[1..10] of integer;
        n                        : integer;

procedure printData;
  var   i: integer;
  begin
        i := 1;
        while i <= n do
          begin
            writeln(A[i]);
            i := i + 1
          end
  end;

procedure culculate;
  var   i, s: integer;
  begin
        s := Sum * Sum;
        i := 1 + 1;
        while i <= n do
          begin
            Sum := Sum + A[i];
            s := s + A[i] * A[i];
            if A[i] > Max then
	      begin
	       Max := A[i]
	      end
	    else
	      begin
	        if A[i] < Min then
	          begin
	            Min := A[i]
	          end
	      end;
	    i := i + 1
          end;
        Mean := Sum div n;
        V := (s - Sum * Mean) div (n - 1)
  end;

procedure sort;   { bubble sort }
  var   low, check, i, temp: integer;
  begin
        low := 1;
        while low < n do
          begin
            check := n;
            i := n;
            while i >= (low + 1) do
              begin
                if A[i-1] > A[i] then
                  begin
		    temp := A[i-1];
		    A[i-1] := A[i];
                    A[i] := temp;
                    check := i
                  end;
                i := i - 1
              end;
            low := check
          end
  end;

begin
      n := 10;          { const for the number of integers }
      A[1] := 1;
      A[2] := 10;
      A[3] := -100;
      A[4] := 3276;
      A[5] := -3276;
      A[6] := 0;
      A[7] := 100;
      A[8] := -10;
      A[9] := -1;
      A[10] := 123;
      writeln(n);
      writeln('Data : ');
      printData;
      sort;
      writeln('Data (sorted) :');
      printData;
      Sum := A[1];
      Max := A[1];
      Min := A[1];
      Mean := A[1];
      V := A[1];
      culculate;
      writeln('SUM      : ', Sum);
      writeln('MAX      : ', Max);
      writeln('MIN      : ', Min);
      writeln('MEAN     : ', Mean);
      writeln('VARIENCE : ', V)
end.
