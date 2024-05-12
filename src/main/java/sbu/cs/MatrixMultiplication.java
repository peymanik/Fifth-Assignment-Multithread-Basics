package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> matrix_A = new ArrayList<>();
        List<List<Integer>> matrix_B = new ArrayList<>();
        int part;
        List<List<Integer>> tempMatrixProduct = new ArrayList<>();
        public BlockMultiplier(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B, int part ) {
            // TODO
            this.matrix_A = matrix_A;
            this.matrix_B = matrix_B;
            this.part = part;
        }
        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            if (part == 1){
                int resault=0;
                for (int i=0 ; i< matrix_A.size()/2; i++){
                    List<Integer> line = new ArrayList<>();
                    for (int j=0 ; j<matrix_B.get(0).size()/2 ; j++){
                        resault = 0;
                        for (int k=0 ; k<matrix_A.get(0).size() ; k++)
                            resault = resault + (matrix_A.get(i).get(k) * matrix_B.get(k).get(j));
                        line.add(resault);
                    }
                    tempMatrixProduct.add(line);
                }
            }
            if (part == 2){
                int resault=0;
                for (int i=0 ; i< matrix_A.size()/2; i++){
                    List<Integer> line = new ArrayList<>();
                    for (int j=matrix_B.get(0).size()/2 ; j<matrix_B.get(0).size() ; j++){
                        resault = 0;
                        for (int k=0 ; k<matrix_A.get(0).size() ; k++)
                            resault = resault + (matrix_A.get(i).get(k) * matrix_B.get(k).get(j));
                        line.add(resault);
                    }
                    tempMatrixProduct.add(line);
                }
            }
            if (part == 3){
                int resault=0;
                for (int i=matrix_A.size()/2 ; i< matrix_A.size(); i++){
                    List<Integer> line = new ArrayList<>();
                    for (int j=0 ; j<matrix_B.get(0).size()/2 ; j++){
                        resault = 0;
                        for (int k=0 ; k<matrix_A.get(0).size() ; k++)
                            resault = resault + (matrix_A.get(i).get(k) * matrix_B.get(k).get(j));
                        line.add(resault);
                    }
                    tempMatrixProduct.add(line);
                }
            }
            if (part == 4){
                int resault=0;
                for (int i=matrix_A.size()/2 ; i< matrix_A.size(); i++){
                    List<Integer> line = new ArrayList<>();
                    for (int j=matrix_B.get(0).size()/2 ; j<matrix_B.get(0).size() ; j++){
                        resault = 0;
                        for (int k=0 ; k<matrix_A.get(0).size() ; k++)
                            resault = resault + (matrix_A.get(i).get(k) * matrix_B.get(k).get(j));
                        line.add(resault);
                    }
                    tempMatrixProduct.add(line);
                }
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        BlockMultiplier multiplier_1 = new BlockMultiplier(matrix_A, matrix_B, 1);
        BlockMultiplier multiplier_2 = new BlockMultiplier(matrix_A, matrix_B, 2);
        BlockMultiplier multiplier_3 = new BlockMultiplier(matrix_A, matrix_B, 3);
        BlockMultiplier multiplier_4 = new BlockMultiplier(matrix_A, matrix_B, 4);
        Thread task_1 = new Thread(multiplier_1);
        Thread task_2 = new Thread(multiplier_2);
        Thread task_3 = new Thread(multiplier_3);
        Thread task_4 = new Thread(multiplier_4);

        task_1.start();
        task_2.start();
        task_3.start();
        task_4.start();

        try {
            task_1.join();
            task_2.join();
            task_3.join();
            task_4.join();
        } catch (InterruptedException e) {throw new RuntimeException(e);}


        List<List<Integer>> finalMatrix = new ArrayList<>();
        for (int i=0 ; i < multiplier_1.tempMatrixProduct.size() ; i++){
            List<Integer> line1 = new ArrayList<>();
            for (int j=0 ; j<multiplier_1.tempMatrixProduct.get(0).size() ; j++)
                line1.add(multiplier_1.tempMatrixProduct.get(i).get(j));

            for (int j=0 ; j<multiplier_2.tempMatrixProduct.get(0).size() ; j++)
                line1.add(multiplier_2.tempMatrixProduct.get(i).get(j));

            finalMatrix.add(line1);
        }
        for (int i=0 ; i < multiplier_3.tempMatrixProduct.size() ; i++){
            List<Integer> line2 = new ArrayList<>();
            for (int j=0 ; j<multiplier_3.tempMatrixProduct.get(0).size() ; j++)
                line2.add(multiplier_3.tempMatrixProduct.get(i).get(j));

            for (int j=0 ; j<multiplier_4.tempMatrixProduct.get(0).size() ; j++)
                line2.add(multiplier_4.tempMatrixProduct.get(i).get(j));

            finalMatrix.add(line2);
        }

        return finalMatrix;
    }

    public static void main(String[] args) {
        // Test your code here
//        List<List<Integer>> a = new ArrayList<>();
//        List<Integer> line1 = new ArrayList<>();
//        List<Integer> line2 = new ArrayList<>();
//        a.add(line1);
//        a.add(line2);
//        a.get(0).add(1);
//        a.get(0).add(2);
//        a.get(0).add(3);
//        a.get(0).add(4);
//        a.get(1).add(5);
//        a.get(1).add(6);
//        a.get(1).add(7);
//        a.get(1).add(8);
//
//        line1 = new ArrayList<>();
//        line2 = new ArrayList<>();
//        List<Integer> line3 = new ArrayList<>();
//        List<Integer> line4 = new ArrayList<>();
//
//        List<List<Integer>> b = new ArrayList<>();
//        b.add(line1);
//        b.add(line2);
//        b.add(line3);
//        b.add(line4);
//        b.get(0).add(1);
//        b.get(0).add(2);
//        b.get(0).add(3);
//        b.get(0).add(4);
//        b.get(1).add(5);
//        b.get(1).add(6);
//        b.get(1).add(7);
//        b.get(1).add(8);
//        b.get(2).add(9);
//        b.get(2).add(10);
//        b.get(2).add(11);
//        b.get(2).add(12);
//        b.get(3).add(13);
//        b.get(3).add(14);
//        b.get(3).add(15);
//        b.get(3).add(16);

//        List<List<Integer>> f = new ArrayList<>();
//        f = ParallelizeMatMul(a,b);

//        System.out.println(f.size());
//        System.out.println(f.get(0).size());
//        System.out.println(f.get(0).get(0));
//        System.out.println(f.get(0).get(1));
//        System.out.println(f.get(1).get(0));
//        System.out.println(f.get(1).get(1));

//        BlockMultiplier bb1 = new BlockMultiplier(a,b,1);
//        BlockMultiplier bb2 = new BlockMultiplier(a,b,2);
//        BlockMultiplier bb3 = new BlockMultiplier(a,b,3);
//        BlockMultiplier bb4 = new BlockMultiplier(a,b,4);
//        bb1.run();
//        bb2.run();
//        bb3.run();
//        bb4.run();
//





    }
}

