#include <iostream>
#include <string.h>

static const bool debug_mode = true;

/*
Time complexity: O(2)
		void replace_char(char *input, unsigned int length)
		{
O(1)			unsigned int i = 0; 
O(1)			unsigned int j = 0;
O(n)			for (i = 0; i < length; i++)
			{
				if (input[i] == ' ')
				{
O(n)	 	 			for (j = length; j > i; j--)
					{
						input[j+2] = input[j];
O(1)					}
					
O(1)					input[i] = '&';
O(1)					input[i+1] = '3';
O(1)					input[i+2] = '2';
O(1)					length+=2;
				}
			}
		}

O(1)+O(1)+O(n)*(O(n) + O(1)+ O(1)+ O(1)+ O(1)) = O(2)+O(n)*O(n+4) = O(2) + O(n^2+4n) = O(2) + O(n^2) = O(n^2)

Space complexity: O(1) 
	Tha auxiliary spaces used are counters i and j, not related to input size.
*/

void replace_char(char *input, unsigned int length)
{
	unsigned int i = 0;
	unsigned int j = 0;
	for (i = 0; i < length; i++)
	{
		if (input[i] == ' ')
		{
		    if (debug_mode)
    			std::cout << "add synbols at position " << i << std::endl;
	
			for (j = length; j > i; j--)
			{
	            if (debug_mode)
    				std::cout << "replace position" << j+2 << " by " << j << std::endl;
				input[j+2] = input[j];
	            if (debug_mode)
    				std::cout << "partial: " << input << std::endl;
			}
			
			input[i] = '&';
			input[i+1] = '3';
			input[i+2] = '2';
			length+=2;
	        if (debug_mode)
    			std::cout << "add synbols: " << input << std::endl;
		}
	}
}

int main()
{
	char *my_array = (char *) malloc(100*sizeof (char));
	
	strcpy(my_array, "User is not allowed");
	std::cout << my_array << std::endl;
	replace_char(my_array, 19);
	std::cout << my_array << std::endl;
	return 0;
}
