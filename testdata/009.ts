program	SPROGRAM	17	1
weekdayCalculation	SIDENTIFIER	43	1
(	SLPAREN	33	1
input	SIDENTIFIER	43	1
,	SCOMMA	41	1
output	SIDENTIFIER	43	1
)	SRPAREN	34	1
;	SSEMICOLON	37	1
var	SVAR	21	2
table	SIDENTIFIER	43	2
:	SCOLON	38	2
array	SARRAY	1	2
[	SLBRACKET	35	2
1	SCONSTANT	44	2
..	SRANGE	39	2
12	SCONSTANT	44	2
]	SRBRACKET	36	2
of	SOF	14	2
integer	SINTEGER	11	2
;	SSEMICOLON	37	2
Year	SIDENTIFIER	43	3
,	SCOMMA	41	3
Month	SIDENTIFIER	43	3
,	SCOMMA	41	3
Day	SIDENTIFIER	43	3
,	SCOMMA	41	3
Result	SIDENTIFIER	43	3
:	SCOLON	38	3
integer	SINTEGER	11	3
;	SSEMICOLON	37	3
procedure	SPROCEDURE	16	5
dayofWeek	SIDENTIFIER	43	5
(	SLPAREN	33	5
Y	SIDENTIFIER	43	5
,	SCOMMA	41	5
M	SIDENTIFIER	43	5
,	SCOMMA	41	5
D	SIDENTIFIER	43	5
:	SCOLON	38	5
integer	SINTEGER	11	5
)	SRPAREN	34	5
;	SSEMICOLON	37	5
var	SVAR	21	6
z	SIDENTIFIER	43	6
,	SCOMMA	41	6
N	SIDENTIFIER	43	6
:	SCOLON	38	6
integer	SINTEGER	11	6
;	SSEMICOLON	37	6
begin	SBEGIN	2	7
if	SIF	10	8
M	SIDENTIFIER	43	8
=	SEQUAL	24	8
1	SCONSTANT	44	8
then	STHEN	19	8
begin	SBEGIN	2	8
z	SIDENTIFIER	43	9
:=	SASSIGN	40	9
Y	SIDENTIFIER	43	9
-	SMINUS	31	9
1901	SCONSTANT	44	9
end	SEND	8	10
else	SELSE	7	11
begin	SBEGIN	2	11
if	SIF	10	12
M	SIDENTIFIER	43	12
=	SEQUAL	24	12
2	SCONSTANT	44	12
then	STHEN	19	12
begin	SBEGIN	2	12
z	SIDENTIFIER	43	13
:=	SASSIGN	40	13
Y	SIDENTIFIER	43	13
-	SMINUS	31	13
1901	SCONSTANT	44	13
end	SEND	8	14
else	SELSE	7	15
begin	SBEGIN	2	15
z	SIDENTIFIER	43	16
:=	SASSIGN	40	16
Y	SIDENTIFIER	43	16
-	SMINUS	31	16
1900	SCONSTANT	44	16
end	SEND	8	17
end	SEND	8	18
;	SSEMICOLON	37	18
N	SIDENTIFIER	43	20
:=	SASSIGN	40	20
(	SLPAREN	33	20
4	SCONSTANT	44	20
+	SPLUS	30	20
z	SIDENTIFIER	43	20
+	SPLUS	30	20
(	SLPAREN	33	20
z	SIDENTIFIER	43	20
div	SDIVD	5	20
4	SCONSTANT	44	20
)	SRPAREN	34	20
+	SPLUS	30	20
table	SIDENTIFIER	43	20
[	SLBRACKET	35	20
M	SIDENTIFIER	43	20
]	SRBRACKET	36	20
+	SPLUS	30	20
(	SLPAREN	33	20
D	SIDENTIFIER	43	20
-	SMINUS	31	20
1	SCONSTANT	44	20
)	SRPAREN	34	20
)	SRPAREN	34	20
;	SSEMICOLON	37	20
while	SWHILE	22	21
N	SIDENTIFIER	43	21
>=	SGREATEQUAL	28	21
7	SCONSTANT	44	21
do	SDO	6	21
N	SIDENTIFIER	43	22
:=	SASSIGN	40	22
N	SIDENTIFIER	43	22
-	SMINUS	31	22
7	SCONSTANT	44	22
;	SSEMICOLON	37	22
Result	SIDENTIFIER	43	23
:=	SASSIGN	40	23
N	SIDENTIFIER	43	23
end	SEND	8	24
;	SSEMICOLON	37	24
procedure	SPROCEDURE	16	26
printDay	SIDENTIFIER	43	26
(	SLPAREN	33	26
Y	SIDENTIFIER	43	26
,	SCOMMA	41	26
M	SIDENTIFIER	43	26
,	SCOMMA	41	26
D	SIDENTIFIER	43	26
,	SCOMMA	41	26
DD	SIDENTIFIER	43	26
:	SCOLON	38	26
integer	SINTEGER	11	26
)	SRPAREN	34	26
;	SSEMICOLON	37	26
begin	SBEGIN	2	27
writeln	SWRITELN	23	28
(	SLPAREN	33	28
Y	SIDENTIFIER	43	28
,	SCOMMA	41	28
'-'	SSTRING	45	28
,	SCOMMA	41	28
M	SIDENTIFIER	43	28
,	SCOMMA	41	28
'-'	SSTRING	45	28
,	SCOMMA	41	28
D	SIDENTIFIER	43	28
,	SCOMMA	41	28
' IS'	SSTRING	45	28
)	SRPAREN	34	28
;	SSEMICOLON	37	28
if	SIF	10	29
DD	SIDENTIFIER	43	29
=	SEQUAL	24	29
0	SCONSTANT	44	29
then	STHEN	19	29
begin	SBEGIN	2	29
writeln	SWRITELN	23	30
(	SLPAREN	33	30
'SUNDAY'	SSTRING	45	30
)	SRPAREN	34	30
end	SEND	8	31
else	SELSE	7	32
begin	SBEGIN	2	32
if	SIF	10	33
DD	SIDENTIFIER	43	33
=	SEQUAL	24	33
1	SCONSTANT	44	33
then	STHEN	19	33
begin	SBEGIN	2	33
writeln	SWRITELN	23	34
(	SLPAREN	33	34
'MONDAY'	SSTRING	45	34
)	SRPAREN	34	34
end	SEND	8	35
else	SELSE	7	36
begin	SBEGIN	2	36
if	SIF	10	37
DD	SIDENTIFIER	43	37
=	SEQUAL	24	37
2	SCONSTANT	44	37
then	STHEN	19	37
begin	SBEGIN	2	37
writeln	SWRITELN	23	38
(	SLPAREN	33	38
'TUESDAY'	SSTRING	45	38
)	SRPAREN	34	38
end	SEND	8	39
else	SELSE	7	40
begin	SBEGIN	2	40
if	SIF	10	41
DD	SIDENTIFIER	43	41
=	SEQUAL	24	41
3	SCONSTANT	44	41
then	STHEN	19	41
begin	SBEGIN	2	41
writeln	SWRITELN	23	42
(	SLPAREN	33	42
'WEDNESDAY'	SSTRING	45	42
)	SRPAREN	34	42
end	SEND	8	43
else	SELSE	7	44
begin	SBEGIN	2	44
if	SIF	10	45
DD	SIDENTIFIER	43	45
=	SEQUAL	24	45
4	SCONSTANT	44	45
then	STHEN	19	45
begin	SBEGIN	2	45
writeln	SWRITELN	23	46
(	SLPAREN	33	46
'THURSDAY'	SSTRING	45	46
)	SRPAREN	34	46
end	SEND	8	47
else	SELSE	7	48
begin	SBEGIN	2	48
if	SIF	10	49
DD	SIDENTIFIER	43	49
=	SEQUAL	24	49
5	SCONSTANT	44	49
then	STHEN	19	49
begin	SBEGIN	2	49
writeln	SWRITELN	23	50
(	SLPAREN	33	50
'FRIDAY'	SSTRING	45	50
)	SRPAREN	34	50
end	SEND	8	51
else	SELSE	7	52
begin	SBEGIN	2	52
if	SIF	10	53
DD	SIDENTIFIER	43	53
=	SEQUAL	24	53
6	SCONSTANT	44	53
then	STHEN	19	53
begin	SBEGIN	2	53
writeln	SWRITELN	23	54
(	SLPAREN	33	54
'SATURDAY'	SSTRING	45	54
)	SRPAREN	34	54
end	SEND	8	55
end	SEND	8	56
end	SEND	8	57
end	SEND	8	58
end	SEND	8	59
end	SEND	8	60
end	SEND	8	61
end	SEND	8	62
;	SSEMICOLON	37	62
begin	SBEGIN	2	64
table	SIDENTIFIER	43	65
[	SLBRACKET	35	65
1	SCONSTANT	44	65
]	SRBRACKET	36	65
:=	SASSIGN	40	65
5	SCONSTANT	44	65
;	SSEMICOLON	37	65
table	SIDENTIFIER	43	66
[	SLBRACKET	35	66
2	SCONSTANT	44	66
]	SRBRACKET	36	66
:=	SASSIGN	40	66
1	SCONSTANT	44	66
;	SSEMICOLON	37	66
table	SIDENTIFIER	43	67
[	SLBRACKET	35	67
3	SCONSTANT	44	67
]	SRBRACKET	36	67
:=	SASSIGN	40	67
0	SCONSTANT	44	67
;	SSEMICOLON	37	67
table	SIDENTIFIER	43	68
[	SLBRACKET	35	68
4	SCONSTANT	44	68
]	SRBRACKET	36	68
:=	SASSIGN	40	68
3	SCONSTANT	44	68
;	SSEMICOLON	37	68
table	SIDENTIFIER	43	69
[	SLBRACKET	35	69
5	SCONSTANT	44	69
]	SRBRACKET	36	69
:=	SASSIGN	40	69
5	SCONSTANT	44	69
;	SSEMICOLON	37	69
table	SIDENTIFIER	43	70
[	SLBRACKET	35	70
6	SCONSTANT	44	70
]	SRBRACKET	36	70
:=	SASSIGN	40	70
1	SCONSTANT	44	70
;	SSEMICOLON	37	70
table	SIDENTIFIER	43	71
[	SLBRACKET	35	71
7	SCONSTANT	44	71
]	SRBRACKET	36	71
:=	SASSIGN	40	71
3	SCONSTANT	44	71
;	SSEMICOLON	37	71
table	SIDENTIFIER	43	72
[	SLBRACKET	35	72
8	SCONSTANT	44	72
]	SRBRACKET	36	72
:=	SASSIGN	40	72
6	SCONSTANT	44	72
;	SSEMICOLON	37	72
table	SIDENTIFIER	43	73
[	SLBRACKET	35	73
9	SCONSTANT	44	73
]	SRBRACKET	36	73
:=	SASSIGN	40	73
2	SCONSTANT	44	73
;	SSEMICOLON	37	73
table	SIDENTIFIER	43	74
[	SLBRACKET	35	74
10	SCONSTANT	44	74
]	SRBRACKET	36	74
:=	SASSIGN	40	74
4	SCONSTANT	44	74
;	SSEMICOLON	37	74
table	SIDENTIFIER	43	75
[	SLBRACKET	35	75
11	SCONSTANT	44	75
]	SRBRACKET	36	75
:=	SASSIGN	40	75
0	SCONSTANT	44	75
;	SSEMICOLON	37	75
table	SIDENTIFIER	43	76
[	SLBRACKET	35	76
12	SCONSTANT	44	76
]	SRBRACKET	36	76
:=	SASSIGN	40	76
2	SCONSTANT	44	76
;	SSEMICOLON	37	76
Year	SIDENTIFIER	43	77
:=	SASSIGN	40	77
1991	SCONSTANT	44	77
;	SSEMICOLON	37	77
Month	SIDENTIFIER	43	78
:=	SASSIGN	40	78
1	SCONSTANT	44	78
;	SSEMICOLON	37	78
Day	SIDENTIFIER	43	79
:=	SASSIGN	40	79
1	SCONSTANT	44	79
;	SSEMICOLON	37	79
dayofWeek	SIDENTIFIER	43	80
(	SLPAREN	33	80
Year	SIDENTIFIER	43	80
,	SCOMMA	41	80
Month	SIDENTIFIER	43	80
,	SCOMMA	41	80
Day	SIDENTIFIER	43	80
)	SRPAREN	34	80
;	SSEMICOLON	37	80
printDay	SIDENTIFIER	43	81
(	SLPAREN	33	81
Year	SIDENTIFIER	43	81
,	SCOMMA	41	81
Month	SIDENTIFIER	43	81
,	SCOMMA	41	81
Day	SIDENTIFIER	43	81
,	SCOMMA	41	81
Result	SIDENTIFIER	43	81
)	SRPAREN	34	81
end	SEND	8	82
.	SDOT	42	82