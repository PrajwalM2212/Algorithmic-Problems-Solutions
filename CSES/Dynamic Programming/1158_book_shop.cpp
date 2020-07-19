// Problem Link: https://cses.fi/problemset/task/1158/
 
#include <bits/stdc++.h>
// #include</Users/prajwalm/Desktop/stdc++.h>
using namespace std;
 
 
/*
--------------------------------------------
Suppose the book prices are given as,
[4, 5, 6, 2]
 
In this problem 'j' means all the books 
from index j.
 
Therefore,
j=0 means [4, 5, 6, 2]
j=1 means [5, 6, 2]
j=2 means [6, 2]
j=3 means [2]
j=4 means []
--------------------------------------------
'x' means the max sum of money we can expend 
--------------------------------------------
solve_r (x, j) means the max pages that
can be chosen given sum x and all the books
from index j
 
solve_r is the recursive implementation with
memoization
----------------------------------------------
solve_i is the iterative implementation
----------------------------------------------
cache[][] is the DP table
-----------------------------------------------
cache[i][j] gives the solution when sum of money
is x and all the books from index j are available 
to choose from
--------------------------------------------------
*/
 
int n, x;
vector<int> prices(n);
vector<int> pages(n);
vector<vector<int>> cache;
 
 
// recursive
int solve_r(int x, int j){
 
    // return the solution of already solved problem
    if(cache[x][j] != 0){
        return cache[x][j];
    }
 
    // no books left to choose from
    if(j==n){
        return 0;
    }
 
    // variables to hold the sum of pages 
    // when book j is chosen and not chosen
    int a=0, b=0;
 
    // if book j can be chosen
    // then choose book j
    if(x-prices[j] >= 0){
        a = pages[j] + solve_r(x-prices[j], j+1);
    }
 
    // not choose book j
    b = solve_r(x, j+1);
 
    // return the max of choosing book j and not choosing book j
    cache[x][j] = max(a, b);
 
    return cache[x][j];
 
}
 
// iterative
int solve_i(){
 
    // construct the DP table
    for(int i=0; i<=x; i++){
        for(int j=n; j>=0; j--){
 
            // no books left to choose from 
            if(j==n){
                cache[i][j] = 0;
                continue;
            }
 
            // variables to hold the sum of pages 
            // when book j is chosen and not chosen
            int a=0, b=0;
 
            // if book j can be chosen
            // then choose book j
            if(i-prices[j] >= 0){
                a = pages[j] + cache[i-prices[j]][j+1];
            }
 
            // not choose book j
            b = cache[i][j+1];
 
            // solution to (i, j) is max of 
            // choosing book j and not choosing book j
            cache[i][j] = max(a, b);
 
        }
    }
 
    // return the max num of pages chosen
    // given sum x and all the books to choose from 
    return cache[x][0];
 
}
 
 
 
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
 
    // read in number of books 
    // and max price to expend
    cin>>n>>x;
 
    // read in the prices of the books
    for(int i=0; i<n; i++){
        int k;
        cin>>k;
        prices.push_back(k);
    }
 
    // read in the number of pages in the book
    for(int i=0; i<n; i++){
        int k;
        cin>>k;
        pages.push_back(k);
    }
 
    // fill in cache with 0's
    for(int i=0; i<x+1; i++){
        vector<int> row;
        for(int j=0; j<n+1; j++){
            row.push_back(0);
        }
        cache.push_back(row);
    }
 
    // print the max num of pages chosen
    // given sum x and all the books to choose from 
    // cout<<solve_r(x, 0)<<endl;
    cout<<solve_i()<<endl;
    return 0;
}