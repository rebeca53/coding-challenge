#include <iostream>
#include <string>

/*
Time complexity:
    void RemoveDuplicates()
    {	
O(1)	Node *cursor = nullptr;
O(1)	Node *parent = nullptr;
O(1)	Node *aux = this;
O(n)	while (aux)
	{
	O(1)	cursor = aux->next;
	O(1)	parent = cursor;

	O(n)	while (cursor)
		{
		O(1)	if (cursor->key.compare(aux->key) != 0)
			{
		O(1)		parent = cursor;
			}
			else
			{
		O(1)		parent->next = cursor->next;
		O(1)		delete cursor;
			}
		O(1)	cursor = cursor->next;
		}
	O(1)	aux = aux->next;
	}
    }
O(3)+O(n)*(O(2)+O(n)*O(5)+O(1))= O(3)+O(n)*O(5n+3) = O(3)+O(5n^2+3n)=O(5n^2+3n+3) =>O(n^2)

Space complexity: O(n)
*/

class Node
{
    public:
    std::string key;
    Node *next;

    void RemoveDuplicates()
    {	
	Node *cursor = nullptr;
	Node *parent = nullptr;
	Node *aux = this;
	while (aux)
	{
		cursor = aux->next;
		parent = cursor;

		while (cursor)
		{
			if (cursor->key.compare(aux->key) != 0)
			{
				parent = cursor;
			}
			else
			{
				parent->next = cursor->next;
				delete cursor;
			}
			cursor = cursor->next;
		}
		aux = aux->next;
	}
    }

    void Print(std::string log = "")
    {
	std::cout << log << std::endl;
	Node *aux = this;
        while (aux)
        {
            std::cout << aux->key << std::endl;
            aux = aux->next;
        }
    }
};

int main()
{
    
    Node * head = nullptr;
    Node * second = nullptr;
    Node * third = nullptr;
    Node * fourth = nullptr;
    Node * fifth = nullptr;
    Node * sixth = nullptr;

    head = new Node();
    second = new Node();
    third = new Node();
    fourth = new Node();
    fifth = new Node();
    sixth = new Node();

    head->key = "oi";
    head->next = second;

    second->key = "bye";
    second->next = third;

    third->key = "bye";
    third->next = fourth;

    fourth->key = "bye";
    fourth->next = fifth;

    fifth->key = "oi";
    fifth->next = sixth;

    sixth->key = "bye";
    sixth->next = nullptr;
    
    head->Print();
    
    head->RemoveDuplicates();
    head->Print("removing duplicates...");

}

