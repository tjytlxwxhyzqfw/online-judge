import os
import subprocess

HDOJ_DIR = "/home/wcc/HDOJ/"
INPUTS_DIR = "/home/wcc/HDOJ/Inputs/"
DEFAULT_INPUT_FILE = "/home/wcc/HDOJ/Inputs/pytest"

def pinput(path=DEFAULT_INPUT_FILE):
	input_file = open(path, "r")
	print "------"
	print input_file.read()
	print "------"
	input_file.close()

def get_output(inputs):
	"""Get output of shell command(s).

		Args:
			Inputs: str(*shell command*), or list(*list of shell commands*)

		Return:
			Return output strings of shell command(s), all outputs are 
			**strip()**ed before returned.
			Return error message if command gets wrong.
	"""

	def __do_get_output(string):
		p = subprocess.Popen(
			string, shell=True,
			stdout=subprocess.PIPE,
			stderr=subprocess.STDOUT);
		return p.communicate()[0].strip()

	return (__do_get_output(inputs) if isinstance(inputs, str)
		else [__do_get_output(cmd) for cmd in inputs])

def simple_test_2(cmd1, cmd2, ginput):
	case = 0
	while True:
		ginput()
		pinput()

		out1 = get_output(cmd1)
		out2 = get_output(cmd2)
		print "case #%d: out1: %s, out2: %s"%(case, out1, out2)
		case = case + 1;

		if not out1 == out2:
			print "GOT YOU !!!"
			break
