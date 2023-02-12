package edu.neu.coe.info6205.union_find;
import java.util.Random;
import java.util.Scanner;
public class UFClientClass {
    private static int[] count(int n) {
            UF_HWQUPC uf = new UF_HWQUPC(n);
            int connections = 0;
            int pairs = 0;
            Random r = new Random();
            while(uf.components()>1){
                int p = r.nextInt(n);
                int q = r.nextInt(n);
                if(!uf.connected(p,q)){
                    uf.union(p,q);
                    connections +=1;
                }
                pairs +=1;

            }
            int[] connectCount = {connections,pairs};
            return connectCount;
        }
        public static void main(String[] args) {

            {

                Scanner sc = new Scanner(System.in);
                System.out.println("Number of objects");
                while(sc.hasNext()) {

                    int N = sc.nextInt();
                    int count[] = count(N);
                    int total =0;
                    for(int i=0;i<50;i++) {
                        total+= count[1];
                    }
                    int avg = total/50;
                    System.out.println("objects = " + N + " with pairs of " + avg + " and connections "+ count[0]);
                    System.out.println("Number of objects");
                }
            }
        }
    }
