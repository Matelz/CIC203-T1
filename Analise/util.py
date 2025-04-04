import pandas as pd
import matplotlib.pyplot as plt

sizes = {
        "100000": "30",
        "200000": "30",
        "400000": "30",
        "800000": "30",
        "1600000": "10",
    }

algorithms = [
    "linearSearch",
    "bubbleSort",
    "selectionSort",
    "insertionSort",
    "binarySearch",
]

def read_files(prefix):
    times = {}

    for size, count in sizes.items():
        times[size] = pd.read_csv(f"./Data/{prefix}_{size}_{count}.csv", header=None, encoding="utf-16");

    return times

def generate_ops_plot():
    data = pd.read_csv("./Relatorios/ops_stats.csv")

    sizes = data["Size"]
    search_algorithms = ["BinarySearch", "LinearSearch"]
    sort_algorithms = ["BubbleSort", "InsertionSort", "SelectionSort"]

    plt.figure(figsize=(10, 6))
    for algo in search_algorithms:
        plt.plot(sizes, data[algo], marker='o', label=algo)
    plt.title("Número de Operações - Algoritmos de Busca")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Número de Operações")
    plt.xticks(sizes, rotation=45)
    plt.grid()
    plt.legend()
    plt.savefig("searching_algorithms_ops.png")
    plt.show()

    plt.figure(figsize=(10, 6))
    for algo in sort_algorithms:
        plt.plot(sizes, data[algo], marker='o', label=algo)
    plt.title("Número de Operações - Algoritmos de Ordenação")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Número de Operações")
    plt.xticks(sizes, rotation=45)
    plt.grid()
    plt.legend()
    plt.savefig("sorting_algorithms_ops.png")
    plt.show()

def generate_time_plot():
    data = pd.read_csv("./Relatorios/time_stats.csv")

    sizes = data["Tamanho"]
    search_algorithms = ["BinarySearch", "LinearSearch"]
    sort_algorithms = ["BubbleSort", "InsertionSort", "SelectionSort"]

    plt.figure(figsize=(10, 6))
    for algo in search_algorithms:
        plt.plot(sizes, data[algo], marker='o', label=algo)
    plt.title("Tempo de Execução - Algoritmos de Busca")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Tempo de Execução (ms)")
    plt.xticks(sizes, rotation=45)
    plt.grid()
    plt.legend()
    plt.savefig("searching_algorithms_time.png")
    plt.show()

    plt.figure(figsize=(10, 6))
    for algo in sort_algorithms:
        plt.plot(sizes, data[algo], marker='o', label=algo)
    plt.title("Tempo de Execução - Algoritmos de Ordenação")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Tempo de Execução (ms)")
    plt.xticks(sizes, rotation=45)
    plt.grid()
    plt.legend()
    plt.savefig("sorting_algorithms_time.png")
    plt.show()

    # Comparação entre busca binária e busca linear
    plt.figure(figsize=(10, 6))

    # Calcular o tempo total para busca binária com cada algoritmo de ordenação
    for sort_algo in sort_algorithms:
        binary_search_times = data[sort_algo] + data["BinarySearch"]
        plt.plot(sizes, binary_search_times, marker='o', label=f"Busca Binária ({sort_algo} + Busca)")

    # Tempo de busca linear
    linear_search_times = data["LinearSearch"]
    plt.plot(sizes, linear_search_times, marker='o', label="Busca Linear")

    plt.title("Comparação de Eficiência - Busca Binária com Ordenações vs Busca Linear")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Tempo de Execução (ms)")
    plt.xticks(sizes, rotation=45)
    plt.grid()
    plt.legend()
    plt.savefig("binary_vs_linear_search_with_sort_comparison.png")
    plt.show()

if __name__ == "__main__":
    generate_time_plot()
    generate_ops_plot()