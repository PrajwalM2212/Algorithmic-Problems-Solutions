#include<bits/stdc++.h>
//#include</Users/prajwalm/Desktop/stdc++.h>
using namespace std;
typedef vector<vector<int>> v;

int solve(int x, int cmin, vector<int> coins, v cache){
   
   if(cache[x][cmin] > 0){
     //cout<<"accessing cache"<<endl;
     return cache[x][cmin];
   }

   if(x==0){
     cache[x][cmin] = 1;
     return 1;
   }
   
   if(x<cmin){
     cache[x][cmin] = -1;
     return -1;
   }

   int count = 0;

   for(int c: coins){
     if(x-c>=0 && c>=cmin){
       int k = solve(x-c,c,coins,cache);
       if(k>0){
         count += k;
       }
     }
  }
  cache[x][cmin] = count;
  // cout<<x<<" "<<cmin<<" "<<count<<endl;
  return count;
}

int main(){

 int n, x;
 cin>>n>>x;
 vector<int> coins(n);

 for(int i=0; i<n; i++){
   cin>>coins[i];
 }
  
 v cache(x+1, vector<int>(n,0));
 cout<<solve(x,0,coins,cache)<<endl; 
 return 0;
}
