public class UnionFind {
    // TODO: Instance variables
    private int[] arr;
    private int size;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        arr = new int[N];
        size = N;
        for(int i = 0 ; i< N ;i ++){
            arr[i] = -1; //全部初始化为-1
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if(arr[v] < 0){
            //如果是小于0则是根节点
            return -arr[v];
        }else{
            //通过递归返回父元素的size
            return sizeOf(arr[v]);
        }
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return arr[v]; //好像完美的整合了根节点和非根节点
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int parent1 = v1;
        int parent2 = v2;

        //找出根节点
        while(arr[parent1] >= 0){
            parent1 = parent(parent1);
        }
        while(arr[parent2] >= 0){
            parent2 = parent(parent2);
        }
        //比较
        if(parent1 == parent2){
            return true;
        }else{
            return false;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        //先使用非路径压缩的版本
        if(v >= size){
            throw new IllegalArgumentException();
        }
        int root = v;
	if(root < 0){
		return root;
	}
        while(parent(root) >= 0){
            root = parent(root);
        }
	// TODO: ADD PATH-COMPRESSION
	// System.out.println("this is the value to find:"+v);
	int current = v;
	int p_current = parent(current);
	while(p_current >= 0){
		arr[current] = root;
		current = p_current;
		p_current = parent(current);//Upadte the current and parent of current
		
	}
	
        return root;
	
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        //检查相同情况
        if(v1 == v2){
            return;
        }

        int parent1 = v1;
        int parent2 = v2;

        //找出根节点
        while(arr[parent1] > 0){
            parent1 = parent(parent1);
        }
        while(arr[parent2] > 0){
            parent2 = parent(parent2);
        }

        //此时的parent都是指向根节点的元素，而不是值
        //将2节点的根节点指向1的根节点
        if(sizeOf(parent2) >= sizeOf(parent1) ){
            arr[parent1] = parent2;
            arr[parent2] = -(sizeOf(parent2) + sizeOf(parent1));
        }else{
            arr[parent2] = parent1;
            arr[parent1] = -(sizeOf(parent2) + sizeOf(parent1));
        }


    }

    public static void main(String[] args) {
        UnionFind union = new UnionFind(10);
        System.out.println(union.find(3));
        union.union(1,1);
        union.union(1,2);
        union.union(3,1);
        union.union(0,3);
        System.out.println(union.find(3)); 
	System.out.println(union.parent(3));
	System.out.println("fixed the path to get the root of 0:"+union.parent(0));	
    }

}
