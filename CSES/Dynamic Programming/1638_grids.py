# Problem Link: https://cses.fi/problemset/task/1638
 
# recursive solution
# solve(i, j) can be read out as move to cell defined by (i, j)
def solve_r(i, j):
 
    mod = 1000000007
 
    # return the solution if the problem is already solved
    if cache[i][j] != 0:
        return cache[i][j]
 
    # base condition
    # at the bottom right corner
    if (i==n-1) and (j==n-1):
        return 1
 
    # variables to keep track of obstacles and boundaries
    b_r = False # boundary_right
    b_d = False # boundary_down
    o_r = False # obstacle_right
    o_d = False # obstacle_down
 
    # Fill the variables defined above
    if i==n-1:
        b_d = True
 
    if j==n-1:
        b_r = True
    
    if not b_d:
        if grids[i+1][j] == '*':
            o_d = True
 
    if not b_r:
        if grids[i][j+1] == '*':
            o_r = True
 
    
    # compute the solution
    if o_r and o_d:
        # if there is obstacle on both right and down move nowhere 
        return cache[i][j]
 
    elif b_r:
        if not o_d:
            # if on right boundary and there is no obstacle down
            # then move down
            cache[i][j] = solve_r(i+1, j) % mod
 
    elif b_d:
        if not o_r:
            # if on bottom boundary and there is no obstacle to the right
            # then move right
            cache[i][j] = solve_r(i, j+1) % mod
 
    elif o_r:
        # if obstacle on right, move down
        cache[i][j] = solve_r(i+1, j) % mod
    
    elif o_d:
        # if obstacle on down, move right
        cache[i][j] = solve_r(i, j+1) % mod
 
    else:
        # no obstacle on right or down
        cache[i][j] = ((solve_r(i+1, j) % mod)  + (solve_r(i, j+1) % mod)) % mod
 
    return cache[i][j]
 
def solve_i():
 
    for i in range(n-1, -1, -1):
        for j in range(n-1, -1, -1):
 
            mod = 1000000007
 
            # base condition
            # at the bottom right corner
            if (i==n-1) and (j==n-1):
                cache[i][j] =  1
                continue
 
            # variables to keep track of obstacles and boundaries
            b_r = False # boundary_right
            b_d = False # boundary_down
            o_r = False # obstacle_right
            o_d = False # obstacle_down
 
            # Fill the variables defined above
            if i==n-1:
                b_d = True
 
            if j==n-1:
                b_r = True
    
            if not b_d:
                if grids[i+1][j] == '*':
                    o_d = True
 
            if not b_r:
                if grids[i][j+1] == '*':
                    o_r = True
 
    
            # compute the solution
            if o_r and o_d:
            # if there is obstacle on both right and down move nowhere 
                cache[i][j] = 0
 
            elif b_r:
                if not o_d:
                # if on right boundary and there is no obstacle down
                # then move down
                    cache[i][j] = cache[i+1][j] % mod
 
            elif b_d:
                if not o_r:
                # if on bottom boundary and there is no obstacle to the right
                # then move right
                    cache[i][j] = cache[i][j+1] % mod
 
            elif o_r:
                # if obstacle on right, move down
                cache[i][j] = cache[i+1][j] % mod
    
            elif o_d:
                # if obstacle on down, move right
                cache[i][j] = cache[i][j+1] % mod
 
            else:
                # no obstacle on right or down
                cache[i][j] = ((cache[i+1][j] % mod)  + (cache[i][j+1] % mod)) % mod
 
 
    return cache[0][0]
 
if __name__ == '__main__':

    from sys import stdin, stdout
    from time import perf_counter

    start = perf_counter()
    n = int(stdin.readline())
 
    grids = []
    cache = []
 
    # read the grid
    grids = [[j for j in stdin.readline()] for i in range(n)]

    # fill the cache for memoization
    cache = [ [0 for j in range(n)] for i in range(n)]
 
    if grids[0][0] == '*':
        # special case when the start position itself is an obstacle
        print(0)
    else:
        # print(solve_r(0,0))
        print(solve_i())
    
    stop = perf_counter()
    print(stop -start)
