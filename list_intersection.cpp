#include <iostream>
#include <string>

class Node
{
    public:
    std::string key;
    Node *next;

    void Print(std::string log = "")
    {
		std::cout << log << std::endl;
		Node *aux = this;
        while (aux)
        {
            std::cout << "pointer " << aux << ",key " << aux->key << std::endl;
            aux = aux->next;
        }
    }
};

/*
Time complexity: O(n^2)

Node *GetIntersection(Node *node1, Node *node2, bool debug = false)
{
O(1)	Node *aux1 = node1;
O(1)	Node *aux2 = node2;

O(n)	while (aux1)
	    {
	O(1)	aux2 = node2;
	O(n)	while (aux2)
			{
		O(1)	if (aux1 == aux2)
				{
					return aux1;
				}
			
		O(1)	aux2 = aux2->next;
			}
		
	O(1) aux1 = aux1->next;
	}

	return nullptr;
};

O(2)+O(n)*(O(2)+O(n)*(O(2)))= O(2)+O(n)*O(2n+2) = O(2)+O(2n^2+2n)=O(2n^2+2n+2) =>O(n^2)

Space complexity: O(n)
*/

Node *GetIntersection(Node *node1, Node *node2, bool debug = false)
{
	Node *aux1 = node1;
	Node *aux2 = node2;

	while (aux1)
	{
		aux2 = node2;
		while (aux2)
		{
			if (debug)
				std::cout << "compare node " << aux1 << " and node " << aux2 << std::endl;
			if (aux1 == aux2)
			{
				if (debug)
					std::cout << "found intersection at "<<aux1 << std::endl;
				return aux1;
			}
			
			aux2 = aux2->next;
		}
		
		aux1 = aux1->next;
	}

	if (debug)
		std::cout << "Intersection not found" << std::endl;
	return nullptr;
};

int main()
{
    
    Node * nodeA = nullptr;
    Node * nodeB = nullptr;
    Node * nodeC = nullptr;
    Node * nodeD = nullptr;
    Node * nodeE = nullptr;
    Node * nodeF = nullptr;
    Node * nodeH = nullptr;
    Node * nodeJ = nullptr;
	Node * nodeI = nullptr;

    nodeA = new Node();
    nodeB = new Node();
    nodeC = new Node();
    nodeD = new Node();
    nodeE = new Node();
    nodeF = new Node();
    nodeH = new Node();
    nodeJ = new Node();
	nodeI = new Node();
 
    nodeC->key = "c";
    nodeC->next = nodeA;

    nodeA->key = "a";
    nodeA->next = nodeE;

    nodeE->key = "e";
    nodeE->next = nodeH;

    nodeH->key = "h";
    nodeH->next = nodeJ;

    nodeJ->key = "j";
    nodeJ->next = nodeB;

    nodeB->key = "b";
    nodeB->next = nodeI;

	nodeI->key = "a";
	nodeI->next = nullptr;

    nodeD->key = "d";
    nodeD->next = nodeF;

    nodeF->key = "f";
    nodeF->next = nodeJ;
    
    nodeC->Print("nodeC list:");
    nodeD->Print("nodeD list:");

    Node *intersection = GetIntersection(nodeC, nodeD);

	if(intersection)
	{
		std::cout << "Intersection node: " << intersection << " with key " << intersection->key << std::endl;
	}
	else
	{
		std::cout << "Intersection not found." << std::endl;
	}
}
