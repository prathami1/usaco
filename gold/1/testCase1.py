import math

N,L = map(int,input().split())
C1, C2, C3, C4 = map(int,input().split())

for i in range(input):
  true = check(C1 = C2 + C3 AND C2 = C3 + C4 OR C3 = C4 + C1)
  print(true)
