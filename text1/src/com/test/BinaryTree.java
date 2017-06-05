package com.test;


/**
 * 二叉树的大多数都是以遍历实现的
 * chenjx
 */
public class BinaryTree {
//    public static void main(String[] args){
//        ChainTreeManager manager = new ChainTreeManager();
//        //插入节点操作
//        ChinaTree<string> tree = CreateRoot();
//        //插入节点数据
//        AddNode(tree);
//        //先序遍历
//        Console.WriteLine("\n先序结果为： \n");
//        manager.BinTree_DLR(tree);
//        //中序遍历
//        Console.WriteLine("\n中序结果为： \n");
//        manager.BinTree_LDR(tree);
//        //后序遍历
//        Console.WriteLine("\n后序结果为： \n");
//        manager.BinTree_LRD(tree);
//        //层次遍历
//        Console.WriteLine("\n层次结果为： \n");
//        manager.Length = 100;
//        manager.BinTree_Level(tree);
//        Console.WriteLine("\n树的深度为：" + manager.BinTreeLen(tree) + "\n");
//        Console.ReadLine();
//    }
    //生成根节点
//    static ChinaTree<string> CreateRoot()
//    {
//        ChinaTree<string> tree = new ChinaTree<string>();
//        Console.WriteLine("请输入根节点，方便我们生成树\n");
//        tree.data = Console.ReadLine();
//        Console.WriteLine("根节点已经生成\n");
//        return tree;
//    }

    //插入节点操作

//    static ChinaTree<string> AddNode(ChinaTree<string> tree)
//    {
//        ChainTreeManager mananger = new ChainTreeManager();
//        while (true)
//        {
//            ChinaTree<string> node = new ChinaTree<string>();
//            Console.WriteLine("请输入要插入节点的数据\n");
//            node.data = Console.ReadLine();
//            Console.WriteLine("请输入要查找的父节点的数据\n");
//            var parentData = Console.ReadLine();
//            bool flag = mananger.BinTreeFind(tree, parentData);
//            if (!flag)
//            {
//                Console.WriteLine("未找到你输入的父节点，请重新输入");
//                continue;
//            }
//            Console.WriteLine("你确定要插到父节点的：1 左侧，2右侧");
//            Direction direction = (Direction)Enum.Parse(typeof(Direction), Console.ReadLine());
//            tree = mananger.BinTreeAddNode(tree, node, parentData, direction);
//            Console.WriteLine("是否继续？  1 继续， 2 退出");
//            if (int.Parse(Console.ReadLine()) == 1)
//                continue;
//            else
//                break;
//        }
//        return tree;
//
//    }
}
