<%
Function getCod(varFiliacao, varTotal, varEndIP)

	dim j, vd, vi, i1, i, byte0, s1, s2, s3, k, k1, j1, l1

	j  = Int(varFiliacao)

	'i1 = Int(varTotal)
	vd = cint(right(varTotal,2)) /10            'localiza valor decimal
	vi = cint(left(varTotal,len(varTotal)-3))   'valor inteiro
	i1 = vi

	i = Second(Now())

	byte0 = 0

	Select Case i
		case 0			byte0 =	11
		case 1			byte0 =	17
		case 2			byte0 =	21
		case 3			byte0 =	31
		case 4			byte0 =	56
		case 5			byte0 =	34
		case 6			byte0 =	42
		case 7			byte0 =	3
		case 8			byte0 =	18
		case 9			byte0 =	13
		case 10			byte0 =	12
		case 11			byte0 =	18
		case 12			byte0 =	22
		case 13			byte0 =	32
		case 14			byte0 =	57
		case 15			byte0 =	35
		case 16			byte0 =	43
		case 17			byte0 =	4
		case 18			byte0 =	19
		case 19			byte0 =	14
		case 20			byte0 =	9
		case 21			byte0 =	20
		case 22			byte0 =	23
		case 23			byte0 =	33
		case 24			byte0 =	58
		case 25			byte0 =	36
		case 26			byte0 =	44
		case 27			byte0 =	5
		case 28			byte0 =	24
		case 29			byte0 =	15
		case 30			byte0 =	62
		case 31			byte0 =	25
		case 32			byte0 =	34
		case 33			byte0 =	59
		case 34			byte0 =	37
		case 35			byte0 =	45
		case 36			byte0 =	6
		case 37			byte0 =	25
		case 38			byte0 =	16
		case 39			byte0 =	27
		case 40			byte0 =	63
		case 41			byte0 =	26
		case 42			byte0 =	35
		case 43			byte0 =	60
		case 44			byte0 =	38
		case 45			byte0 =	46
		case 46			byte0 =	7
		case 47			byte0 =	26
		case 48			byte0 =	17
		case 49			byte0 =	28
		case 50			byte0 =	14
		case 51			byte0 =	36
		case 52			byte0 =	2
		case 53			byte0 =	39
		case 54			byte0 =	47
		case 55			byte0 =	8
		case 56			byte0 =	29
		case 57			byte0 =	22
		case 58			byte0 =	55
		case 59			byte0 =	33
	End Select

	i = byte0

	if (i < 10) then
		s2 = "0"
	else
		s2 = ""
	end if


	s1 = varEndIP


	k1 = Len(s1)
	j1 = i1 + i
	l1 = i + k1
	k = i * j
	s3 = k

	getCod = K & "" & j1 & "" & l1 & "-" & Len(s3) & "" & s2 & "" & i

End Function
%>
