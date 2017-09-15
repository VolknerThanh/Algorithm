package algorithm;

import java.util.*;

public class Algorithm {
    
    public static void Increasing(int[] a)
    {
        for(int i = 0; i < a.length - 1; i++)
        {
            for(int j = i + 1; j < a.length; j++)
            {
                if(a[i] > a[j])
                {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
    
    public static void Decreasing(int[] a)
    {
        for(int i = 0; i < a.length - 1; i++)
        {
            for(int j = i + 1; j < a.length; j++)
            {
                if(a[i] < a[j])
                {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
    
    public static boolean isCoinsisive(int[] a)
    {
        // Hàm kiểm tra trùng phần tử khi tạo ngẫu nhiên các số trong dãy...
        boolean f = false;
        
        for(int i = 0; i < a.length - 1; i++)
        {
            for(int j = i + 1; j < a.length; j++)
            {
                if(a[i] == a[j])
                    f = true;
            }
        }
        
        return f;
    }
    
    public static boolean isExisted(int x, int[] a)
    {
        // Hàm kiểm tra 1 giá trị có tồn tại trong dãy hay không...
        boolean f = false;
        for(int i = 0; i < a.length; i++)
        {
            if(x == a[i])
                f = true;
        }
        return f;
    }
    
    public static int[] createArrayRandom()
    {
        // khai báo input để nhận dữ liệu từ bàn phím...
        Scanner input = new Scanner(System.in);
        // Kiểm tra số có vượt quá 200 hay không...
        int n;
        do{
            // nhập vào chiều dài của chuỗi queue cần xét...
            System.out.print("Enter the number of the queue: ");
            n = input.nextInt();
            if(n >= 200)
                System.out.println("the number should be in range[0...199] !!! Try again !!!");
        } while(n >= 200);
        // tạo dãy với số lượng vừa nhập ở trên...
        int[] array = new int[n];
        // kiểm tra trùng...
        do{
            for(int i = 0; i < n; i++)
            {
                // tao số ngẫu nhiên từ 0 -> 199...
                array[i] = (int)(Math.random() * 200);
            }
        } while(isCoinsisive(array));
        // Xuất dãy...
        return array;
    }
    
    public static void Output(int[] a)
    {
        // Hàm in dữ liệu trong dãy...
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
    
    public static int ChooseNumber(int[] a)
    {
        int n;
        // khai báo input để nhận dữ liệu từ bàn phím...
        Scanner input = new Scanner(System.in);
        // Kiểm tra có trùng với số trong dãy hoặc vượt quá khoảng [0...199] hay không...
        do{
            System.out.print("Choose the number to start: ");
            n = input.nextInt();
            if(n < 0 || n > 199)
                System.out.println("This number should be in the range of [0...199]. Try another number !!! ");
            else if(isExisted(n, a))
                System.out.println("This number must be different with the the number in array !!!");
        } while(n < 0 || n > 199 || isExisted(n, a));
        return n;
    }
    
    /**
     * Các Giải Thuật : FCFS, SSTF, SCAN, C-SCAN, LOOK, C-LOOK
     */
    
    public static void Algorithm_FCFS(int x, int[] a)
    {
        System.out.println("Algorithm FCFS : ");
        System.out.format("%d", x);
        for(int i = 0; i < a.length; i++)
        {
            System.out.format(" - %d", a[i]);
        }
        System.out.println();
    }
        
    public static void Algorithm_SSTF(int x, int[] a)
    {
        ArrayList array = new ArrayList();
        for(int i = 0; i < a.length; i++)
            array.add(a[i]);
        System.out.format("%d" , x);
        for(int i = 0; i < a.length; i++)
        {
            int min = -1;
            int tmp = -1;
            int vt = 0;
            for(int j = 0; j < array.size(); j++)
            {
                if(min == -1)
                {
                    min = Math.abs(x - (int)array.get(j));
                    tmp = (int)array.get(j);
                    vt = j;
                }
                if(Math.abs(x - (int)array.get(j)) < min)
                {
                    min = Math.abs(x - (int)array.get(j));
                    tmp = (int)array.get(j);
                    vt = j;
                }
            }
            System.out.format(" - %d" ,tmp);
            x = tmp;
            array.remove(vt);
        } System.out.println();
    }
        
    public static void Algorithm_SCAN(int x, int[] a)
    {
        System.out.println("Algorithm SCAN : ");
        System.out.format("%d", x);
        // Đếm số lớn hơn số bắt đầu, số nhỏ hơn bằng tổng độ dài trừ cho số lớn hơn...
        int Big = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > x)
                Big++;
        }
        // Tạo 2 mảng nhỏ để lát ghép lại thành chuỗi cần tìm...
        int[] Bigger = new int[Big + 1];             // Mảng chứa số lớn hơn số bắt đầu...
        int[] Smaller = new int[a.length - Big]; // Mảng chứa số nhỏ hơn số bắt đầu...
        // Khai báo vị trí cho 2 mảng nhỏ mới tạo...
        int BigIndex = -1, SmallIndex = -1;
        for(int i = 0; i < a.length; i++)
        {
            // Đưa các phần tử từ mảng cha vào 2 mảng con phù hợp...
            if(a[i] > x)
            {
                BigIndex++;
                Bigger[BigIndex] = a[i];
            }
            else
            {
                SmallIndex++;
                Smaller[SmallIndex] = a[i];
            }
        }
        // Thêm vào biên phải...
        BigIndex++;
        Bigger[BigIndex] = 199;
        // Sắp xếp mảng chứa số lớn hơn tăng dần và số nhỏ hơn giảm dần...
        Increasing(Bigger);
        Decreasing(Smaller);
        for(int i = 0; i < a.length; i++)
        {
            if(i < Bigger.length)
                System.out.format(" - %d", Bigger[i]);
            else
                System.out.format(" - %d", Smaller[i - Bigger.length]);
        }
        System.out.println();
    }
    
    public static void Algorithm_CSCAN(int x, int[] a)
    {
        System.out.println("Algorithm C-SCAN : ");
        System.out.format("%d", x);
        // Đếm số lớn hơn số bắt đầu, số nhỏ hơn bằng tổng độ dài trừ cho số lớn hơn...
        int Big = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > x)
                Big++;
        }
        // Tạo 2 mảng nhỏ để lát ghép lại thành chuỗi cần tìm...
        int[] Bigger = new int[Big + 1];             // Mảng chứa số lớn hơn số bắt đầu...
        int[] Smaller = new int[a.length - Big + 1]; // Mảng chứa số nhỏ hơn số bắt đầu...
        // Khai báo vị trí cho 2 mảng nhỏ mới tạo...
        int BigIndex = -1, SmallIndex = -1;
        // Thêm vào biên trái...
        SmallIndex++;
        Smaller[SmallIndex] = 0;
        for(int i = 0; i < a.length; i++)
        {
            // Đưa các phần tử từ mảng cha vào 2 mảng con phù hợp...
            if(a[i] > x)
            {
                BigIndex++;
                Bigger[BigIndex] = a[i];
            }
            else
            {
                SmallIndex++;
                Smaller[SmallIndex] = a[i];
            }
        }
        // Thêm vào biên phải...
        BigIndex++;
        Bigger[BigIndex] = 199;
        // Sắp xếp mảng chứa số lớn hơn tăng dần và số nhỏ hơn tăng dần...
        Increasing(Bigger);
        Increasing(Smaller);
        // In ra...
        for(int i = 0; i < a.length + 2; i++) // a.length + 2 vì cộng thêm 2 giá trị biên...
        {
            if(i < Bigger.length)
                System.out.format(" - %d", Bigger[i]);
            else
                System.out.format(" - %d", Smaller[i - Bigger.length]);
        }
        System.out.println();
    }
    
    public static void Algorithm_LOOK(int x, int[] a)
    {
        System.out.println("Algorithm LOOK : ");
        System.out.format("%d", x);
        // Đếm số lớn hơn số bắt đầu, số nhỏ hơn bằng tổng độ dài trừ cho số lớn hơn...
        int Big = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > x)
                Big++;
        }
        // Tạo 2 mảng nhỏ để lát ghép lại thành chuỗi cần tìm...
        int[] Bigger = new int[Big];             // Mảng chứa số lớn hơn số bắt đầu...
        int[] Smaller = new int[a.length - Big]; // Mảng chứa số nhỏ hơn số bắt đầu...
        // Khai báo vị trí cho 2 mảng nhỏ mới tạo...
        int BigIndex = -1, SmallIndex = -1;
        for(int i = 0; i < a.length; i++)
        {
            // Đưa các phần tử từ mảng cha vào 2 mảng con phù hợp...
            if(a[i] > x)
            {
                BigIndex++;
                Bigger[BigIndex] = a[i];
            }
            else
            {
                SmallIndex++;
                Smaller[SmallIndex] = a[i];
            }
        }
        // Sắp xếp mảng chứa số lớn hơn tăng dần và số nhỏ hơn giảm dần...
        Increasing(Bigger);
        Decreasing(Smaller);
        for(int i = 0; i < a.length; i++)
        {
            if(Bigger.length <= Smaller.length) {
                if(i < Bigger.length)
                    System.out.format(" - %d", Bigger[i]);
                else
                    System.out.format(" - %d", Smaller[i - Bigger.length]);
            }
            else {
                if(i < a.length - Bigger.length)
                    System.out.format(" - %d", Smaller[i]);
                else
                    System.out.format(" - %d", Bigger[i - Smaller.length]);
            }
        }
        System.out.println();
    }
    
    public static void Algorithm_CLOOK(int x, int[] a)
    {
        System.out.println("Algorithm C-LOOK : ");
        System.out.format("%d", x);
        // Đếm số lớn hơn số bắt đầu, số nhỏ hơn bằng tổng độ dài trừ cho số lớn hơn...
        int Big = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > x)
                Big++;
        }
        // Tạo 2 mảng nhỏ để lát ghép lại thành chuỗi cần tìm...
        int[] Bigger = new int[Big];
        int[] Smaller = new int[a.length - Big];
        // Khai báo vị trí cho 2 mảng nhỏ mới tạo...
        int BigIndex = -1, SmallIndex = -1;
        for(int i = 0; i < a.length; i++)
        {
            // Đưa các phần tử từ mảng cha vào 2 mảng con phù hợp...
            if(a[i] > x)
            {
                BigIndex++;
                Bigger[BigIndex] = a[i];
            }
            else
            {
                SmallIndex++;
                Smaller[SmallIndex] = a[i];
            }
        }
        // Sắp xếp mảng chứa số lớn hơn tăng dần và số nhỏ hơn tăng dần...
        Increasing(Bigger);
        Increasing(Smaller);
        // In ra...
        for(int i = 0; i < a.length; i++)
        {
            if(i < Bigger.length)
                System.out.format(" - %d", Bigger[i]);
            else
                System.out.format(" - %d", Smaller[i - Bigger.length]);
        }
        System.out.println();
    }
    
    public static void main(String[] args) 
    {
        int[] a = createArrayRandom();
        Output(a);
        int n = ChooseNumber(a);
        System.out.println("==================");
//        Algorithm_FCFS(n, a);
//        Algorithm_SSTF(n, a);
//        Algorithm_SCAN(n, a);
//        Algorithm_CSCAN(n, a);
        Algorithm_LOOK(n, a);
//        Algorithm_CLOOK(n, a);
    }
}
