arrayList {1,2,3,4,5,6,7,8}

Collections.rotate(arrayList, 4);
Collections.sort(arrayList);
Collections.shuffle(arrayList);
Collections.sort(arrayList, new Comparator<Integer>() {
	public int compare(Integer o1, Integer o2) {
		return o2.compareTo(o1);
	}
});

并集 addAll  
	System.arraycopy(a, 0, elementData, size, numNew);

交集 retainAll
	 batchRemove(c, true);

差集 removeAll
	 batchRemove(c, false);

	 private boolean batchRemove(Collection<?> c, boolean complement) {
			 final Object[] elementData = this.elementData;
			 int r = 0, w = 0;
			 boolean modified = false;
			 try {
					 for (; r < size; r++)
							 if (c.contains(elementData[r]) == complement)
									 elementData[w++] = elementData[r];
