#include</Users/prajwalm/Desktop/stdc++.h>
// #include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0);
  int n;
  int mod = 1000000007;
  cin>>n;
  vector<int> count(n+1,0);
  
  count[0] = 1;
  
  for(int i=1; i<=n; i++){
    for(int j=1; j<=6; j++){
        if(i-j >= 0){
           count[i] = (count[i]+count[i-j])%mod;
        }
    }
  }
  
  cout<<count[n]<<"\n";
  return 0;
}
