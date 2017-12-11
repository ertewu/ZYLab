//
// Created by wzy on 8/6/17.
// http://www.cnblogs.com/chio/archive/2007/10/23/934345.html
//

#include <iostream>
#include <vector>
#include <cstdlib>

using namespace std;

struct Node {
  char data;
  Node *left;
  Node *right;
}

enum Tag {
  goLeft,
  goRight,
  goBack
}

public void work(){
   Node root,b,c,d,e,f,g,h;
   root.data='a';
   root.left=&b;
   root.right=&e;
}

void printPath(const vector<Node *> &v){

  vector<Node*>::const_iterator vi;
  for(vi=v.begin();vi!=v.end();++vi){
    Node* n=reinterpret_cast<Node *> (*vi);
    cout<<n->data<<" ";
  }
  cout<<endl;
}

void printAllPaths(Node *root){
  asset(root!=NULL);
  vector<Node*> vec_node;
  vector<Tag> vec_flag;

  vec_node.push_back(root);
  vec_flag.push_back(goLeft);

  while(!vec_node.empty()){
     Node *curNode=vec_node.back();
     Tag curFlag=vec_flag.back();

     switch(curFlag){
       case goLeft:
       vec_flag.pop_back();
       vec_flag.push_back(goRight);

       if(curNode->left!=NULL){
          vec_node.push_back(curNode->left);
          vec_flag.push_back(goLeft);
       }
       break;

       case goRight:
       vec_flag.pop_back();
       vec_flag.push_back(goBack);
       if(curNode->right!=NULL) {
          vec_node.push_back(curNode->right);
          vec_flag.push_back(goLeft);
       }
       break;

       case goBack:
       if(NULL==curNode->left&&NULL==curNode->right){
         printPath(vec_node);
       }
       vec_flag.pop_back();
       vec_node.pop_back();
       break;

       default:
       break;
     }
  }
}



