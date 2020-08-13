// Example program
#include <iostream>
#include <string>

class Node
{
    public:
    std::string key;
    Node *next;
    
    void Remove(std::string message)
    {
        if (next->key.compare(message) == 0)
        {
                Node *aux = next;
                next = next->next;
		aux = nullptr;
        }
    }

    void Print()
    {
	Node *aux = this;
        while (aux)
        {
            std::cout << aux->key << std::endl;
            aux = aux->next;
        }
    }
};


void remove_duplicates(Node *list)
{
    Node *cursor = list;
    
    while (cursor)
    {
        cursor->Remove(cursor->key);
    
        cursor = cursor->next;
    }
}


int main()
{
    
    Node * head = nullptr;
    Node * second = nullptr;
    Node * third = nullptr;

    head = new Node();
    second = new Node();
    third = new Node();

    head->key = "oi";
    head->next = second;

    second->key = "bye";
    second->next = third;

    third->key = "bye";
    third->next = NULL;
    
    head->Print();
    
    remove_duplicates(head);
    
    head->Print();

}

