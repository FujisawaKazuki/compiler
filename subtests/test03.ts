program	SPROGRAM	17	1
pas117	SIDENTIFIER	43	1
(	SLPAREN	33	1
output	SIDENTIFIER	43	1
)	SRPAREN	34	1
;	SSEMICOLON	37	1
var	SVAR	21	2
v	SIDENTIFIER	43	2
:	SCOLON	38	2
array	SARRAY	1	2
[	SLBRACKET	35	2
1	SCONSTANT	44	2
..	SRANGE	39	2
100	SCONSTANT	44	2
]	SRBRACKET	36	2
of	SOF	14	2
integer	SINTEGER	11	2
;	SSEMICOLON	37	2
lv	SIDENTIFIER	43	3
:	SCOLON	38	3
array	SARRAY	1	3
[	SLBRACKET	35	3
1	SCONSTANT	44	3
..	SRANGE	39	3
20	SCONSTANT	44	3
]	SRBRACKET	36	3
of	SOF	14	3
integer	SINTEGER	11	3
;	SSEMICOLON	37	3
uv	SIDENTIFIER	43	4
:	SCOLON	38	4
array	SARRAY	1	4
[	SLBRACKET	35	4
1	SCONSTANT	44	4
..	SRANGE	39	4
20	SCONSTANT	44	4
]	SRBRACKET	36	4
of	SOF	14	4
integer	SINTEGER	11	4
;	SSEMICOLON	37	4
p	SIDENTIFIER	43	5
:	SCOLON	38	5
integer	SINTEGER	11	5
;	SSEMICOLON	37	5
pivlin	SIDENTIFIER	43	6
:	SCOLON	38	6
integer	SINTEGER	11	6
;	SSEMICOLON	37	6
temp	SIDENTIFIER	43	7
:	SCOLON	38	7
integer	SINTEGER	11	7
;	SSEMICOLON	37	7
i	SIDENTIFIER	43	8
:	SCOLON	38	8
integer	SINTEGER	11	8
;	SSEMICOLON	37	8
j	SIDENTIFIER	43	9
:	SCOLON	38	9
integer	SINTEGER	11	9
;	SSEMICOLON	37	9
n	SIDENTIFIER	43	10
:	SCOLON	38	10
integer	SINTEGER	11	10
;	SSEMICOLON	37	10
p	SIDENTIFIER	43	11
:	SCOLON	38	11
integer	SINTEGER	11	11
;	SSEMICOLON	37	11
begin	SBEGIN	2	12
n	SIDENTIFIER	43	13
:=	SASSIGN	40	13
20	SCONSTANT	44	13
;	SSEMICOLON	37	13
writeln	SWRITELN	23	14
(	SLPAREN	33	14
'***** quick sort *****'	SSTRING	45	14
)	SRPAREN	34	14
;	SSEMICOLON	37	14
v	SIDENTIFIER	43	15
[	SLBRACKET	35	15
1	SCONSTANT	44	15
]	SRBRACKET	36	15
:=	SASSIGN	40	15
12	SCONSTANT	44	15
;	SSEMICOLON	37	15
i	SIDENTIFIER	43	16
:=	SASSIGN	40	16
2	SCONSTANT	44	16
;	SSEMICOLON	37	16
while	SWHILE	22	17
i	SIDENTIFIER	43	17
<=	SLESSEQUAL	27	17
n	SIDENTIFIER	43	17
do	SDO	6	17
begin	SBEGIN	2	18
v	SIDENTIFIER	43	19
[	SLBRACKET	35	19
i	SIDENTIFIER	43	19
]	SRBRACKET	36	19
:=	SASSIGN	40	19
v	SIDENTIFIER	43	19
[	SLBRACKET	35	19
i	SIDENTIFIER	43	19
-	SMINUS	31	19
1	SCONSTANT	44	19
]	SRBRACKET	36	19
*	SSTAR	32	19
65	SCONSTANT	44	19
+	SPLUS	30	19
17	SCONSTANT	44	19
;	SSEMICOLON	37	19
v	SIDENTIFIER	43	20
[	SLBRACKET	35	20
i	SIDENTIFIER	43	20
]	SRBRACKET	36	20
:=	SASSIGN	40	20
v	SIDENTIFIER	43	20
[	SLBRACKET	35	20
i	SIDENTIFIER	43	20
]	SRBRACKET	36	20
-	SMINUS	31	20
v	SIDENTIFIER	43	20
[	SLBRACKET	35	20
i	SIDENTIFIER	43	20
]	SRBRACKET	36	20
div	SDIVD	5	20
256	SCONSTANT	44	20
*	SSTAR	32	20
256	SCONSTANT	44	20
;	SSEMICOLON	37	20
i	SIDENTIFIER	43	21
:=	SASSIGN	40	21
i	SIDENTIFIER	43	21
+	SPLUS	30	21
1	SCONSTANT	44	21
end	SEND	8	22
;	SSEMICOLON	37	22
writeln	SWRITELN	23	23
(	SLPAREN	33	23
'***** data ******'	SSTRING	45	23
)	SRPAREN	34	23
;	SSEMICOLON	37	23
i	SIDENTIFIER	43	24
:=	SASSIGN	40	24
1	SCONSTANT	44	24
;	SSEMICOLON	37	24
while	SWHILE	22	25
i	SIDENTIFIER	43	25
<=	SLESSEQUAL	27	25
n	SIDENTIFIER	43	25
do	SDO	6	25
begin	SBEGIN	2	26
writeln	SWRITELN	23	27
(	SLPAREN	33	27
v	SIDENTIFIER	43	27
[	SLBRACKET	35	27
i	SIDENTIFIER	43	27
]	SRBRACKET	36	27
,	SCOMMA	41	27
' '	SSTRING	45	27
,	SCOMMA	41	27
v	SIDENTIFIER	43	27
[	SLBRACKET	35	27
i	SIDENTIFIER	43	27
+	SPLUS	30	27
1	SCONSTANT	44	27
]	SRBRACKET	36	27
,	SCOMMA	41	27
' '	SSTRING	45	27
,	SCOMMA	41	27
v	SIDENTIFIER	43	27
[	SLBRACKET	35	27
i	SIDENTIFIER	43	27
+	SPLUS	30	27
2	SCONSTANT	44	27
]	SRBRACKET	36	27
,	SCOMMA	41	27
' '	SSTRING	45	27
,	SCOMMA	41	27
v	SIDENTIFIER	43	27
[	SLBRACKET	35	27
i	SIDENTIFIER	43	27
+	SPLUS	30	27
3	SCONSTANT	44	27
]	SRBRACKET	36	27
,	SCOMMA	41	27
' '	SSTRING	45	27
,	SCOMMA	41	27
v	SIDENTIFIER	43	27
[	SLBRACKET	35	27
i	SIDENTIFIER	43	27
+	SPLUS	30	27
4	SCONSTANT	44	27
]	SRBRACKET	36	27
)	SRPAREN	34	27
;	SSEMICOLON	37	27
i	SIDENTIFIER	43	28
:=	SASSIGN	40	28
i	SIDENTIFIER	43	28
+	SPLUS	30	28
5	SCONSTANT	44	28
end	SEND	8	29
;	SSEMICOLON	37	29
lv	SIDENTIFIER	43	30
[	SLBRACKET	35	30
1	SCONSTANT	44	30
]	SRBRACKET	36	30
:=	SASSIGN	40	30
1	SCONSTANT	44	30
;	SSEMICOLON	37	30
uv	SIDENTIFIER	43	31
[	SLBRACKET	35	31
1	SCONSTANT	44	31
]	SRBRACKET	36	31
:=	SASSIGN	40	31
n	SIDENTIFIER	43	31
;	SSEMICOLON	37	31
p	SIDENTIFIER	43	32
:=	SASSIGN	40	32
1	SCONSTANT	44	32
;	SSEMICOLON	37	32
while	SWHILE	22	33
p	SIDENTIFIER	43	33
>	SGREAT	29	33
0	SCONSTANT	44	33
do	SDO	6	33
begin	SBEGIN	2	34
if	SIF	10	35
lv	SIDENTIFIER	43	35
[	SLBRACKET	35	35
p	SIDENTIFIER	43	35
]	SRBRACKET	36	35
>=	SGREATEQUAL	28	35
uv	SIDENTIFIER	43	35
[	SLBRACKET	35	35
p	SIDENTIFIER	43	35
]	SRBRACKET	36	35
then	STHEN	19	35
begin	SBEGIN	2	35
p	SIDENTIFIER	43	35
:=	SASSIGN	40	35
p	SIDENTIFIER	43	35
-	SMINUS	31	35
1	SCONSTANT	44	35
end	SEND	8	35
else	SELSE	7	36
begin	SBEGIN	2	37
i	SIDENTIFIER	43	38
:=	SASSIGN	40	38
lv	SIDENTIFIER	43	38
[	SLBRACKET	35	38
p	SIDENTIFIER	43	38
]	SRBRACKET	36	38
-	SMINUS	31	38
1	SCONSTANT	44	38
;	SSEMICOLON	37	38
j	SIDENTIFIER	43	39
:=	SASSIGN	40	39
uv	SIDENTIFIER	43	39
[	SLBRACKET	35	39
p	SIDENTIFIER	43	39
]	SRBRACKET	36	39
;	SSEMICOLON	37	39
pivlin	SIDENTIFIER	43	40
:=	SASSIGN	40	40
v	SIDENTIFIER	43	40
[	SLBRACKET	35	40
j	SIDENTIFIER	43	40
]	SRBRACKET	36	40
;	SSEMICOLON	37	40
while	SWHILE	22	41
i	SIDENTIFIER	43	41
<	SLESS	26	41
j	SIDENTIFIER	43	41
do	SDO	6	41
begin	SBEGIN	2	42
i	SIDENTIFIER	43	43
:=	SASSIGN	40	43
i	SIDENTIFIER	43	43
+	SPLUS	30	43
1	SCONSTANT	44	43
;	SSEMICOLON	37	43
while	SWHILE	22	44
v	SIDENTIFIER	43	44
[	SLBRACKET	35	44
i	SIDENTIFIER	43	44
]	SRBRACKET	36	44
<	SLESS	26	44
pivlin	SIDENTIFIER	43	44
do	SDO	6	44
i	SIDENTIFIER	43	44
:=	SASSIGN	40	44
i	SIDENTIFIER	43	44
+	SPLUS	30	44
1	SCONSTANT	44	44
;	SSEMICOLON	37	44
j	SIDENTIFIER	43	45
:=	SASSIGN	40	45
j	SIDENTIFIER	43	45
-	SMINUS	31	45
1	SCONSTANT	44	45
;	SSEMICOLON	37	45
while	SWHILE	22	46
v	SIDENTIFIER	43	46
[	SLBRACKET	35	46
j	SIDENTIFIER	43	46
]	SRBRACKET	36	46
>	SGREAT	29	46
pivlin	SIDENTIFIER	43	46
do	SDO	6	46
begin	SBEGIN	2	47
j	SIDENTIFIER	43	48
:=	SASSIGN	40	48
j	SIDENTIFIER	43	48
-	SMINUS	31	48
1	SCONSTANT	44	48
;	SSEMICOLON	37	48
if	SIF	10	49
i	SIDENTIFIER	43	49
>=	SGREATEQUAL	28	49
j	SIDENTIFIER	43	49
then	STHEN	19	49
begin	SBEGIN	2	49
pivlin	SIDENTIFIER	43	49
:=	SASSIGN	40	49
v	SIDENTIFIER	43	49
[	SLBRACKET	35	49
j	SIDENTIFIER	43	49
]	SRBRACKET	36	49
end	SEND	8	49
end	SEND	8	50
;	SSEMICOLON	37	50
if	SIF	10	51
i	SIDENTIFIER	43	51
<	SLESS	26	51
j	SIDENTIFIER	43	51
then	STHEN	19	51
begin	SBEGIN	2	52
temp	SIDENTIFIER	43	53
:=	SASSIGN	40	53
v	SIDENTIFIER	43	53
[	SLBRACKET	35	53
i	SIDENTIFIER	43	53
]	SRBRACKET	36	53
;	SSEMICOLON	37	53
v	SIDENTIFIER	43	54
[	SLBRACKET	35	54
i	SIDENTIFIER	43	54
]	SRBRACKET	36	54
:=	SASSIGN	40	54
v	SIDENTIFIER	43	54
[	SLBRACKET	35	54
j	SIDENTIFIER	43	54
]	SRBRACKET	36	54
;	SSEMICOLON	37	54
v	SIDENTIFIER	43	55
[	SLBRACKET	35	55
j	SIDENTIFIER	43	55
]	SRBRACKET	36	55
:=	SASSIGN	40	55
temp	SIDENTIFIER	43	55
end	SEND	8	56
end	SEND	8	57
;	SSEMICOLON	37	57
temp	SIDENTIFIER	43	58
:=	SASSIGN	40	58
v	SIDENTIFIER	43	58
[	SLBRACKET	35	58
i	SIDENTIFIER	43	58
]	SRBRACKET	36	58
;	SSEMICOLON	37	58
v	SIDENTIFIER	43	59
[	SLBRACKET	35	59
i	SIDENTIFIER	43	59
]	SRBRACKET	36	59
:=	SASSIGN	40	59
v	SIDENTIFIER	43	59
[	SLBRACKET	35	59
uv	SIDENTIFIER	43	59
[	SLBRACKET	35	59
p	SIDENTIFIER	43	59
]	SRBRACKET	36	59
]	SRBRACKET	36	59
;	SSEMICOLON	37	59
v	SIDENTIFIER	43	60
[	SLBRACKET	35	60
uv	SIDENTIFIER	43	60
[	SLBRACKET	35	60
p	SIDENTIFIER	43	60
]	SRBRACKET	36	60
]	SRBRACKET	36	60
:=	SASSIGN	40	60
temp	SIDENTIFIER	43	60
;	SSEMICOLON	37	60
if	SIF	10	61
i	SIDENTIFIER	43	61
-	SMINUS	31	61
lv	SIDENTIFIER	43	61
[	SLBRACKET	35	61
p	SIDENTIFIER	43	61
]	SRBRACKET	36	61
<	SLESS	26	61
uv	SIDENTIFIER	43	61
[	SLBRACKET	35	61
p	SIDENTIFIER	43	61
]	SRBRACKET	36	61
-	SMINUS	31	61
i	SIDENTIFIER	43	61
then	STHEN	19	61
begin	SBEGIN	2	62
lv	SIDENTIFIER	43	63
[	SLBRACKET	35	63
p	SIDENTIFIER	43	63
+	SPLUS	30	63
1	SCONSTANT	44	63
]	SRBRACKET	36	63
:=	SASSIGN	40	63
lv	SIDENTIFIER	43	63
[	SLBRACKET	35	63
p	SIDENTIFIER	43	63
]	SRBRACKET	36	63
;	SSEMICOLON	37	63
uv	SIDENTIFIER	43	64
[	SLBRACKET	35	64
p	SIDENTIFIER	43	64
+	SPLUS	30	64
1	SCONSTANT	44	64
]	SRBRACKET	36	64
:=	SASSIGN	40	64
i	SIDENTIFIER	43	64
-	SMINUS	31	64
1	SCONSTANT	44	64
;	SSEMICOLON	37	64
lv	SIDENTIFIER	43	65
[	SLBRACKET	35	65
p	SIDENTIFIER	43	65
]	SRBRACKET	36	65
:=	SASSIGN	40	65
i	SIDENTIFIER	43	65
+	SPLUS	30	65
1	SCONSTANT	44	65
end	SEND	8	66
else	SELSE	7	66
begin	SBEGIN	2	67
lv	SIDENTIFIER	43	68
[	SLBRACKET	35	68
p	SIDENTIFIER	43	68
+	SPLUS	30	68
1	SCONSTANT	44	68
]	SRBRACKET	36	68
:=	SASSIGN	40	68
i	SIDENTIFIER	43	68
+	SPLUS	30	68
1	SCONSTANT	44	68
;	SSEMICOLON	37	68
uv	SIDENTIFIER	43	69
[	SLBRACKET	35	69
p	SIDENTIFIER	43	69
+	SPLUS	30	69
1	SCONSTANT	44	69
]	SRBRACKET	36	69
:=	SASSIGN	40	69
uv	SIDENTIFIER	43	69
[	SLBRACKET	35	69
p	SIDENTIFIER	43	69
]	SRBRACKET	36	69
;	SSEMICOLON	37	69
uv	SIDENTIFIER	43	70
[	SLBRACKET	35	70
p	SIDENTIFIER	43	70
]	SRBRACKET	36	70
:=	SASSIGN	40	70
i	SIDENTIFIER	43	70
-	SMINUS	31	70
1	SCONSTANT	44	70
end	SEND	8	71
;	SSEMICOLON	37	71
p	SIDENTIFIER	43	72
:=	SASSIGN	40	72
p	SIDENTIFIER	43	72
+	SPLUS	30	72
1	SCONSTANT	44	72
end	SEND	8	73
end	SEND	8	74
;	SSEMICOLON	37	74
writeln	SWRITELN	23	75
(	SLPAREN	33	75
'***** result ******'	SSTRING	45	75
)	SRPAREN	34	75
;	SSEMICOLON	37	75
i	SIDENTIFIER	43	76
:=	SASSIGN	40	76
1	SCONSTANT	44	76
;	SSEMICOLON	37	76
while	SWHILE	22	77
i	SIDENTIFIER	43	77
<=	SLESSEQUAL	27	77
n	SIDENTIFIER	43	77
do	SDO	6	77
begin	SBEGIN	2	78
writeln	SWRITELN	23	79
(	SLPAREN	33	79
v	SIDENTIFIER	43	79
[	SLBRACKET	35	79
i	SIDENTIFIER	43	79
]	SRBRACKET	36	79
,	SCOMMA	41	79
' '	SSTRING	45	79
,	SCOMMA	41	79
v	SIDENTIFIER	43	79
[	SLBRACKET	35	79
i	SIDENTIFIER	43	79
+	SPLUS	30	79
1	SCONSTANT	44	79
]	SRBRACKET	36	79
,	SCOMMA	41	79
' '	SSTRING	45	79
,	SCOMMA	41	79
v	SIDENTIFIER	43	79
[	SLBRACKET	35	79
i	SIDENTIFIER	43	79
+	SPLUS	30	79
2	SCONSTANT	44	79
]	SRBRACKET	36	79
,	SCOMMA	41	79
' '	SSTRING	45	79
,	SCOMMA	41	79
v	SIDENTIFIER	43	79
[	SLBRACKET	35	79
i	SIDENTIFIER	43	79
+	SPLUS	30	79
3	SCONSTANT	44	79
]	SRBRACKET	36	79
,	SCOMMA	41	79
' '	SSTRING	45	79
,	SCOMMA	41	79
v	SIDENTIFIER	43	79
[	SLBRACKET	35	79
i	SIDENTIFIER	43	79
+	SPLUS	30	79
4	SCONSTANT	44	79
]	SRBRACKET	36	79
)	SRPAREN	34	79
;	SSEMICOLON	37	79
i	SIDENTIFIER	43	80
:=	SASSIGN	40	80
i	SIDENTIFIER	43	80
+	SPLUS	30	80
5	SCONSTANT	44	80
end	SEND	8	81
end	SEND	8	82
.	SDOT	42	82