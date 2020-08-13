
debug_mode = False

def is_missing_char(word_a, word_b):
	size = len(word_a)
	if size < len(word_b):
		if debug_mode:
			print("word a is smaller than word b")
		return False

	for i in range(0,size-1):
		if word_a[i] != word_b[i]:
			if debug_mode:
				print("is " + word_a[i+1] + " equal to " + word_b[i])
			return word_a[i+1] == word_b[i]

	return True

def is_char_inserted(word_a, word_b):
	size = len(word_b)
	if size < len(word_a):
		return False

	for i in range(0,size-1):
		if word_a[i] != word_b[i]:
			return word_a[i] == word_b[i+1]

def is_char_replaced(word_a, word_b):
	size = len(word_a)
	if size != len(word_b):
		return False
	
	replaced_qty = 0
	for i in range(0,size-1):
		if word_a[i] != word_b[i]:
			replaced_qty = replaced_qty + 1

		if replaced_qty > 1:
			return False

	return True

def is_typo(word_a, word_b):
	if debug_mode:
		print("is missing: "+str(is_missing_char(word_a, word_b)))
		print("is inserted: "+str(is_char_inserted(word_a, word_b)))
		print("is replaced: "+str(is_char_replaced(word_a, word_b)))

	return is_missing_char(word_a, word_b) or is_char_inserted(word_a, word_b) or is_char_replaced(word_a, word_b)

print("is word typo pale,ple: " + str(is_typo("pale", "ple")))
print("is word typo pales,pale: " + str(is_typo("pales", "pale")))
print("is word typo pale,bale: " + str(is_typo("pale", "bale")))
print("is word typo pale,bake: " + str(is_typo("pale", "bake")))

						
