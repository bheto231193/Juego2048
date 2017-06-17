package bases;

import java.util.Iterator;

public class lista<T> implements Iterable<T> {

	private nodo<T> primero;
	private nodo<T> ultimo;

	public lista() {
		this.primero = this.ultimo = null;
	}

	public void add(T data) {
		nodo<T> node = new nodo<T>(data);
		if(this.isEmpty()) {
			this.primero = this.ultimo = node;
		} else {
			this.ultimo.setNext(node);
			node.setPrevious(this.ultimo);
			this.ultimo = node;
		}
	}

	public nodo<T> getFirst() {
		return primero;
	}

	public nodo<T> getLast() {
		return ultimo;
	}

	public boolean isEmpty() {
		return this.primero == null && this.ultimo == null;
	}

	public T get(Integer index) {
		T data = null;
		if(!this.isEmpty()) {
			nodo<T> aux = this.primero;
			for(int i = 0; i < index && aux != null ; i++) {
				aux = aux.getNext();
			}
			data = aux.getData();
		}
		return data;
	}

	public void reverse() {
		if(!this.isEmpty()) {
			nodo<T> aux = this.primero;
			nodo<T> aux2 = this.ultimo;

			while(aux != aux2) {
				this.exchange(aux, aux2);
				if(aux != aux2.getPrevious()) {
					aux = aux.getNext();
					aux2 = aux2.getPrevious();
				} else {
					aux = aux2;
				}
			}
		}
	}

	private void exchange(nodo<T> a, nodo<T> b) {
		T data = a.getData();
		a.setData(b.getData());
		b.setData(data);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			nodo<T> aux = primero;

			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T data = aux.getData();
				aux = aux.getNext();

				return data;
			}
		};
	}

	public static void main(String[] args) {
		lista<Integer> i = new lista<>();
		i.add(0);
		i.add(1);
		i.add(2);
		i.add(3);

		Integer a = i.get(3);

		System.out.println(a);
	}

}