package exercise;

class SafetyList {
    // BEGIN
    private int[] elementData;
    private int size;

    public SafetyList() {
        elementData = new int[10];
        size = 0;
    }

    public synchronized void add(int num) {
        if (!ensureCapacity(size + 1)) {
            var newElementData = new int[size + 10];
            for (var i = 0; i < elementData.length; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }
        elementData[size] = num;
        size++;
    }

    public int getSize() {
        return size;
    }

    private boolean ensureCapacity(int newSize) {
        return newSize <= elementData.length;
    }
// END
}
