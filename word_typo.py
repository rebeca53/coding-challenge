
debug_mode = False

#I am considering that len(word_a) is an atomic operation.

#Time complexity:
#def is_missing_char(word_a, word_b):
#O(1)	size = len(word_a)
#O(1)	if size < len(word_b):
#		return False

#O(n)	for i in range(0,size-1):
#	O(1)	if word_a[i] != word_b[i]:
#	O(1)		return word_a[i+1] == word_b[i]

#O(1)+O(1)+O(n)*(O(1)+O(1)) = O(2)+O(n)*O(2) = O(2n+2) => O(n)

#Space complexity: variables size and i are not related to input size, so, the two extra spaces represent O(1).

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

#Time complexity:
#def is_char_inserted(word_a, word_b):
#O(1)	size = len(word_b)
#O(1)	if size < len(word_a):
#		return False

#O(n)	for i in range(0,size-1):
#	O(1)	if word_a[i] != word_b[i]:
#	O(1)		return word_a[i] == word_b[i+1]
#O(1)+O(1)+O(n)*(O(1)+O(1)) = O(2)+O(n)*O(2) = O(2n+2) => O(n)

#Space complexity: variables size and i are not related to input size, so, the two extra spaces represent O(1).

def is_char_inserted(word_a, word_b):
	size = len(word_b)
	if size < len(word_a):
		return False

	for i in range(0,size-1):
		if word_a[i] != word_b[i]:
			return word_a[i] == word_b[i+1]

#Time complexity:
#def is_char_replaced(word_a, word_b):
#O(1)	size = len(word_a)
#O(1)	if size != len(word_b):
#		return False
	
#O(1)	replaced_qty = 0
#O(n)	for i in range(0,size-1):
#O(1)		if word_a[i] != word_b[i]:
#O(1)			replaced_qty = replaced_qty + 1

#O(1)		if replaced_qty > 1:
#O(1)			return False

#	return True
#O(1)+O(1)+O(1)+O(n)*(O(1)+O(1)+O(1)+O(1)) = O(3)+O(n)*O(4) = O(3n+4) => O(n)

#Space complexity: variables size, i and replaced_qty are not related to input size, so, the extra spaces represent O(1).

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

#Finally,
#Time complexity: O(n)
#def is_typo(word_a, word_b):
#O(1)	return is_missing_char(word_a, word_b) or is_char_inserted(word_a, word_b) or is_char_replaced(word_a, word_b)
#Assuming that the three methods will be run one after other followed by a O(1) OR terciary operation,
#O(n)+O(n)+O(n) => O(n)

#Space complexity: Because the space complexity of each method is O(1), the space complexity of all of them, run one after other is still O(1).

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

						
