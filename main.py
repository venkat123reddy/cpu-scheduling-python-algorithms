import pandas as pd
from fcfs import *

def readCsv():
    result = []
    df = pd.read_excel('input.xlsx')
    for index,row in df.iterrows():
        result.append([row['Process'],row['BurstTime'],row['Priority']])

    print(result)
    return result
  



def main():
    while(True):
        inp = readCsv()
        print("=======SCHEDULING ALGORTHIMS===")
        print("1.FCFS")
        print("2.PRIORITY SCHEDULING")
        print("Enter the input number")
        choice = int(input())
        if choice==1:
            fcfs(inp)
            break
        
main()
