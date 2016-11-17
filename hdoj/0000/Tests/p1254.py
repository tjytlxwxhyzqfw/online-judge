def ms(h, w, m):
	s = "%d %d\n"%(h, w);
	for ln in m:
		for n in ln:
			s += "%d "%n;
		s += "\n"
	return s;
