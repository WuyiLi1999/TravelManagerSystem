package com.example.springboottest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student {
    private long speed;
    private long durability;

    public Student(long speed, long durability) {
        this.speed = speed;
        this.durability = durability;
    }

    public long getSpeed() {
        return speed;
    }

    public long getDurability() {
        return durability;
    }
}


public class TeamSelection {
    public static void main(String[] args) {
        // 学生总数
        int num = 200;
        // 选择的学生数
        int k = 10;
        //速度
        int [] speed=
                {73,62,15,79,18,30,62,26,79,48,67,57,40,69,29,99,52,42,45,71,47,42,61,76,30,33,35,92,57,20,
                78,88,5,3,43,44,88,42,82,36,74,5,76,24,7,47,23,55,43,33,38,63,52,30,89,37,38,11,87,79,90,44
                ,93,79,60,77,12,81,36,19,96,6,48,96,66,69,20,31,22,6,90,58,22,77,11,90,14,42,40,1,89,7,78,73,
                70,84,86,32,45,24,31,78,33,11,14,68,28,27,80,26,57,29,32,66,51,83,77,76,21,70,91,85,76,44,
                82,3,31,2,64,34,0,17,10,72,56,17,17,46,12,29,1,78,61,29,38,47,61,35,68,99,75,60,71,42,85,
                20,70,96,48,29,98,3,62,56,70,27,53,21,20,74,18,43,7,23,68,78,95,66,58,5,58,64,8,53,77,90,48
                ,73,17,18,36,95,6,62,30,26,57,89,26,93};

        //{2,10,3,1,5,8};
        //耐力
        int [] durability=
        {1,5,9,4,1,4,5,7,4,2,8,2,4,3,1,2,3,6,4,8,7,10,6,4,8,10,1,1,4,2,5,10,3,1,6,6,2,9,6,8,4,1,
                3,1,1,3,3,10,8,8,7,10,3,10,3,10,8,3,4,1,1,4,8,2,4,2,3,8,10,2,10,1,6,3,3,3,9,9,9,1,3,5,3,9,9,10,2,3,3,10,9,3,7
                ,9,1,1,9,9,3,9,9,3,8,9,5,1,5,6,3,7,10,8,5,1,7,1,7,8,3,4,2,3,6,2,2,10,8,9,7,3,8,6,7,8,5,4,4,10,1,3,2,
                6,10,1,1,10,9,3,7,9,9,10,3,6,3,4,1,1,7,7,1,9,10,9,8,1,7,4,8,9,10,10,4,10,1,2,9,5,8,6,3,7,6,3,6,2,5
                ,8,6,6,6,4,7,3,4,8,2,3,10,5};
        //{5,4,3,9,7,2}
        System.out.println("MaxPerformance:"+new TeamSelection().MaxPerformance(num,speed,durability,k));
    }
    public long MaxPerformance(int num,int [] speed,int [] durability,int k){
        List<Student> students = new ArrayList<>();
        // 假设学生的速度和耐力已知，并存储在students列表中
        for (int i = 0; i < num; i++) {
            students.add(new Student(speed[i],durability[i]));
        }
        // 按照  耐力与速度的乘积 进行升序排序
        students.sort(Comparator.comparingLong(student->student.getDurability()*student.getSpeed()));
        // 选择乘积最大的k个同学（起始指定这样的时候最优）
        List<Student> selectedStudents = students.subList(num-k,num);
//        //1. 假设选择前20个同学的综合值是最优的--截取前20个学生作为团队
//        List<Student> selectedStudents = students.subList(0,k);
        //1.1 计算速度之和
        long speedSum = selectedStudents.stream()
                .mapToLong(Student::getSpeed)
                .sum();
        //排序获取最小的耐力
        Collections.sort(selectedStudents,Comparator.comparingLong(Student::getDurability));

        //1.2 计算最佳组合的综合值
        long compositeValue = speedSum * selectedStudents.get(0).getDurability();

        //遍历剩余的学生，如果当前的学生的速度或者耐力比当前团队的中的最小值大：
        // 1.如果是速度比最小值大：比较加入该同学并移除团队中最小速度的同学之后的综合值和当前综合值的大小，如果大于加入该同学移除最小速速同学（更新团队）
        // 2.如果是耐力比最小值大：比较加入该同学并移除团队中最小耐力的同学之后的综合值和当前综合值的大小，如果大于加入该同学移除最小耐力同学（更新团队）
        // 3.如果不满足，那就遍历下一个学生
        for (int i = num-k-1; i >=0; i--) {
            //1. 获取当前学生数据
            Student cur=students.get(i);
            //2. 记录当前团队中的最小速度学生信息与最小耐力学生信息
            Collections.sort(selectedStudents,Comparator.comparingLong(Student::getSpeed));
            Student minSpeedStu=selectedStudents.get(0);
            Collections.sort(selectedStudents,Comparator.comparingLong(Student::getDurability));
            Student minDurabilityStu =selectedStudents.get(0);
            //3. 记录当前团队中的速度和
            speedSum=selectedStudents.stream().mapToLong(Student::getSpeed).sum();
            //4. 进行当前学生于团队中最小速度和最小耐力学生作比较（分为两种情况）
            //情况一：当前学生的耐力比团队中耐力最小的学生大，判断当前学生是否要加入团队
            //情况二：当前学生的速度比团队中速度最小的学生大，判断当前学生是否要加入团队
            if(cur.getDurability()>minDurabilityStu.getDurability()){
                //4.1 记录更新后的速度和（当前学生选择加入之后的速度和并移除最小耐力学生的速度）
                long newSpeedSum=speedSum-minDurabilityStu.getSpeed()+cur.getSpeed();
                //4.2 获取耐力最小值（判断第二小的耐力与当前学生的耐力的大小）
                long newMinDurability=Math.min(selectedStudents.get(1).getDurability(),cur.getDurability());
                //4.3 计算添加后的学生的综合值与当前综合值的大小：大的话就需要将当前学生添加进团队中并移除最小耐力的学生
                if(compositeValue<newSpeedSum*newMinDurability){
                    selectedStudents.remove(0);
                    selectedStudents.add(cur);
                    compositeValue=newSpeedSum*newMinDurability;
                    continue;
                }
//                System.out.println("curNode-Durability:"+"speedSum:"+newSpeedSum+"   minDur:"+newMinDurability+"   result:"+newSpeedSum*newMinDurability);
            }
            if(cur.getSpeed()>minSpeedStu.getSpeed()){
                //4.1 记录更新后的速度和（当前学生选择加入之后的速度和并移除最小速度学生的速度）
                long newSpeedSum=speedSum-minSpeedStu.getSpeed()+cur.getSpeed();
                //4.2 获取耐力最小值
                long newMinDurability= Math.min(cur.getDurability(),minDurabilityStu.getDurability());
                if (compositeValue<newSpeedSum*newMinDurability){
                    Collections.sort(selectedStudents,Comparator.comparingLong(Student::getSpeed));
                    selectedStudents.remove(0);
                    selectedStudents.add(cur);
                    compositeValue=newSpeedSum*newMinDurability;
                }
//                System.out.println("curNode-speed--->"+"  speedSum:"+newSpeedSum+"minDur:"+newMinDurability+"  result:"+newSpeedSum*newMinDurability);
            }
//            for (int j = 0; j < selectedStudents.size(); j++) {
//                System.out.println(j+"  "+selectedStudents.get(j).getSpeed()+"---"+selectedStudents.get(j).getDurability());
//            }
//            System.out.println(compositeValue);
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }
        return compositeValue;
    }
}