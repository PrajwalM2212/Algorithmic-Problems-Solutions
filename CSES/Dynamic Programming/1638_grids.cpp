// Problem Link: https://cses.fi/problemset/task/1638

//#include<bits/stdc++.h>

// #include </Users/prajwalm/Desktop/stdc++.h>
#include<bits/stdc++.h>
using namespace std;

vector<vector<char>> grids;
vector<vector<int>> cache;
int n;

int solve_i(){
    
    for(int i=n-1; i>=0; i--){
        for(int j=n-1; j>=0; j--){
 
            int mod = 1000000007;
 
            // base condition
            // at the bottom right corner
            if(i==n-1 && j==n-1){
                cache[i][j] =  1;
                continue;
            }
 
            // variables to keep track of obstacles and boundaries
            bool b_r = false; // boundary_right
            bool b_d = false; // boundary_down
            bool o_r = false; // obstacle_right
            bool o_d = false; // obstacle_down
 
            // Fill the variables defined above
            if(i==n-1){
                b_d = true;
            }
 
            if(j==n-1){
                b_r = true;
            }
    
            if(!b_d){
                if(grids[i+1][j] == '*'){
                    o_d = true;
                }
            }
 
            if(!b_r){
                if(grids[i][j+1] == '*'){
                    o_r = true;
                }
            }
 
    
            // compute the solution
            if(o_r && o_d){
            // if there is obstacle on both right and down move nowhere 
                cache[i][j] = 0;
            }
 
            else if (b_r){
                if (!o_d){
                // if on right boundary and there is no obstacle down
                // then move down
                    cache[i][j] = cache[i+1][j] % mod;
                }
            }
            else if (b_d){
                if (!o_r){
                // if on bottom boundary and there is no obstacle to the right
                // then move right
                    cache[i][j] = cache[i][j+1] % mod;
                }
            }
 
            else if (o_r){
                // if obstacle on right, move down
                cache[i][j] = cache[i+1][j] % mod;
            }
    
            else if (o_d){
                // if obstacle on down, move right
                cache[i][j] = cache[i][j+1] % mod;
            }
 
            else{
                // no obstacle on right or down
                cache[i][j] = ((cache[i+1][j] % mod)  + (cache[i][j+1] % mod)) % mod;
            }
        }
    }
 
    return cache[0][0];
}
 
int main(){

    cin>>n;
    
    for(int i=0; i<n; i++){
        vector<char> row;
        string k;
        cin>>k;
        for(char c: k){
            //cout<<c<<endl;
            row.push_back(c);
        }
        grids.push_back(row);
    }

    for(int i=0; i<n; i++){
        vector<int> row;
        for(int j=0; j<n; j++){
            row.push_back(0);
        }
        cache.push_back(row);
    }

    if(grids[0][0] == '*'){
        // special case when the start position itself is an obstacle
        cout<<0<<endl;
    }
    else{
        cout<<solve_i()<<endl;
    }

    return 0;

}
