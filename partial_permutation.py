from __future__ import division

debug_mode = False

# Time complexity: O(n^2)

#	def is_partial_permutation(word_a, word_b):
#O(1)		letters_changed = 0
#
#O(1)		counter_a = 0
#O(n)		for a in word_a:

#    O(1)		counter_b = 0
#    O(n)		for b in word_b:
				
#    O(1)			if counter_a == counter_b:
#    O(1)				if counter_a == 0 and a != b:
#						return False

#    O(1)				if b != a:
#    O(1)					letters_changed = letters_changed + 1

#    O(1)			if b == a:	
#    O(1)				word_b = word_b[:counter_b]+'$' + word_b[counter_b+1:]
#    O(1)			counter_b = counter_b + 1

#O(1)   		counter_a = counter_a + 1

#O(1)		if (letters_changed / counter_a) > (2/3):
#			return False

#O(n)		for b in word_b:
#O(1)			if b != '$':
#				return False

#		return True
#O(1)+O(1)+O(n)*(O(1) + O(n)*(O(7)) + O(1))+O(1)+O(n)*O(1)

#O(2) + O(n)*(O(2) + O(7n)) + O(1) + O(n)
#O(3) + O(n) + O(n)*O(7n+2) 
#O(n+3) + O(7n^2+2n) = O(7n^2+15n+3)
#O(n^2)
#

#Space complexity: O(1).
#Auxiliary spaces are :two counters, and two auxiliary chars to store the value of a position, and the number of letters that changed from word_a to word_b. So, 5 extra spaces, that are not related to input sizes, will represent O(1).

def is_partial_permutation(word_a, word_b):
	letters_changed = 0

	counter_a = 0
	for a in word_a:

		counter_b = 0
		for b in word_b:
			if debug_mode:
				print("a is "+a+" and b is "+b)
			
			if counter_a == counter_b:
				if counter_a == 0 and a != b:
					return False

				if b != a:
					letters_changed = letters_changed + 1

			if b == a:	
				word_b = word_b[:counter_b]+'$' + word_b[counter_b+1:]
			if debug_mode:
				print(word_b)
			counter_b = counter_b + 1

		counter_a = counter_a + 1
		if debug_mode:
			print("counter_a: "+str(counter_a))

	if debug_mode:
		print("letters_changed: " + str(letters_changed) + ", counter_a: " + str(counter_a) + ", letters_changed / counter_a: " + str(letters_changed / counter_a))

	if (letters_changed / counter_a) > (2/3):
		return False

	for b in word_b:
		if b != '$':
			return False

	return True

print("is_partial_permutation(you, yuo)")
print(is_partial_permutation("you", "yuo"))

print("is_partial_permutation(probably, porbalby)")
print(is_partial_permutation("probably", "porbalby"))

print("is_partial_permutation(despite, desptie)")
print(is_partial_permutation("despite", "desptie"))

print("is_partial_permutation(moon, nmoo)")
print(is_partial_permutation("moon", "nmoo"))

print("is_partial_permutation(misspellings, mpeissngslli)")
print(is_partial_permutation("misspellings", "mpeissngslli"))
