#include<bits/stdc++.h>
using namespace std;
typedef vector<vector<int>> vvi;
typedef vector<int> vi;

int max_sum_plates(int n, int k, int p, vvi stacks){
    /*
    cache[i][j] -> Maximum sum that can be obtained by 
                   starting from the ith stack. We are 
                   choosing j plates.
                   
                   If there were 3 stacks,
                    stack 3 can choose plates from stack 3, 2 and 1
                    stack 2 can only choose plates from stack 2 and 1
                    stack 1 can only choose plates from stack 1
                    
    Logic:
    ------
    This is a DP problem. 
    
    Test case: 
    2 4 5
    10 10 100 30
    80 50 10 50
    
    stacks = [[0, 0, 0, 0], [10, 20, 120, 150], [80, 130, 140, 190]]
    
    We start from the 2nd stack ( [80, 130, 140, 190] ). Here we can choose
    80(1 plate), 130(2 plates), 140 (3 plates) or 190(4 plates). Then we will
    have to correspodingly choose 4,3,2 or 1 plates from the first stack to choose
    a total of 5 plates as required. 
    One of these combinations will give the optimal sum.
    
    */
    int cache[n+1][p];
    
    for(int i=n; i>=1; i--){
        for(int j=p; j>=0; j--){
            cache[i][j] = 0;
            vi s = stacks[i];
            for(int l=0; l<s.size(); l++){
              if(j-l >= 0){
                int val = 0;
                if(i+1 <= n && j-l > 0) {
                    val = cache[i+1][j-l];
                }
                cache[i][j] = max(cache[i][j], s[l]+val);
              }
            }
        }
    }
    
    return cache[1][p];
    
}



int main(){
    int t;
    cin>>t;
    int tu = 0;
    while(t>0){
        /*
        Test case: 
        2 4 5
        10 10 100 30
        80 50 10 50
        
        n -> number of stacks, 2
        k -> number of elements in a stack, 4
        p -> number of plates to choose, 5
        stacks -> vector of vectors. The vectors mimic a stack
                  There are n+1 stacks. The first stack is filled
                  with 0's and is not used. This allows us to index 
                  the remaining n stacks from 1.
                  [[0, 0, 0, 0], [10, 20, 120, 150], [80, 130, 140, 190]]
                  
                  Example of a individual stack contents:
                  For stack 1,
                  [10, 20, 120, 150]
                  The stack contains cumulative sums till index i.
                  This is because the if we take a plate from astack
                  then we must also take all the plates above it.
                  
        Then the call to max_sum_plates function returns the answer 
        */
        
        int n, k, p;
        cin>>n>>k>>p;
        vvi stacks;
        vi stack;
        
        for(int j=0; j<=k; j++){
            stack.push_back(0);
        }
        
        stacks.push_back(stack);
        for(int i=1; i<=n; i++){
            int sum = 0;
            vi stack;
            stack.push_back(0);
            for(int j=1; j<=k; j++){
                int val;
                cin>>val;
                sum += val;
                stack.push_back(sum);
            }
            stacks.push_back(stack);
        }
        
        tu++;
        cout<<"Case #"<<tu<<": "<<max_sum_plates(n, k, p, stacks)<<endl;
        t--;
    }
    return 0;
}