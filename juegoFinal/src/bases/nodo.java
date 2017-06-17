package bases;

public class nodo<T> {

	private T data;
	private nodo<T> siguiente;
	private nodo<T> anterior;
	
	public nodo() {
		this.data = null;
		this.siguiente = this.anterior = null;
	}
	
	public nodo(T data) {
		this.data = data;
		this.siguiente = this.anterior = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public nodo<T> getNext() {
		return siguiente;
	}

	public void setNext(nodo<T> next) {
		this.siguiente = next;
	}

	public nodo<T> getPrevious() {
		return anterior;
	}

	public void setPrevious(nodo<T> previous) {
		this.anterior = previous;
	}
	
}