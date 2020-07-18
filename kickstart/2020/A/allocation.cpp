#include<bits/stdc++.h>
using namespace std;
typedef priority_queue<int, vector<int>, greater<int>> pq;

int max_houses(int b, pq costs)
{
    int count = 0;
    
    while(b>=0 && costs.size()>0){
        int cost = costs.top();
        costs.pop();
        if(b-cost >= 0){
            count++;
        }
        b-=cost;
    }
    
    return count;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t;
    cin>>t;
    int k = 0;
    while(t>0){
        int n, b, c;
        cin>>n;
        cin>>b;
        k++;
        pq costs;
        for(int i=0; i<n; i++){
            cin>>c;
            costs.push(c);
        }
        cout<<"Case #"<<k<<": "<<max_houses(b, costs)<<endl;
        t--;
    }
    return 0;
}
        
