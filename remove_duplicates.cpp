// Example program
#include <iostream>
#include <string>

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

