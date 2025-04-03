import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats
import pandas as pd
import util

def plot_histogram(data, size):
    plt.figure(figsize=(10, 6))
    plt.hist(data, bins=30, color='blue', alpha=0.7)
    plt.title(f"Histograma de Tempos de Execução para Tamanho {size}")
    plt.xlabel("Tempo (ms)")
    plt.ylabel("Frequência")
    plt.grid(axis='y', alpha=0.75)
    plt.savefig(f"./Relatorios/histograma_{size}.png")
    plt.show()
    plt.close()

# Função para plotar o desvio padrão no formato de um gráfico de sino
def plot_std(data, size):
    plt.figure(figsize=(10, 6))
    mean = np.mean(data)
    std_dev = np.std(data)
    x = np.linspace(mean - 4*std_dev, mean + 4*std_dev, 1000)
    y = stats.norm.pdf(x, mean, std_dev)
    
    plt.plot(x, y, color='red', label='Distribuição Normal')
    plt.title(f"Distribuição Normal para Tamanho {size}")
    plt.xlabel("Tempo (ms)")
    plt.ylabel("Densidade de Probabilidade")
    plt.legend()
    plt.grid()
    plt.savefig(f"./Relatorios/normal_{size}.png")
    plt.show()
    plt.close()

# Plotar o tempo de execução para cada tamanho de entrada no mesmo gráfico, assim como a função referente à execução teórica O(n^2)
def plot_execution_time(times, sizes):
    plt.figure(figsize=(10, 6))
    for size in sizes:
        plt.plot(times[size][0], label=f"Tamanho {size}")
    
    # Adicionando a função O(n^2)
    n = np.linspace(1, max(map(int, sizes)), 100)
    plt.plot(n, n**2 / 1000000, label="O(n^2)", color='black', linestyle='--')
    
    plt.title("Tempos de Execução para Diferentes Tamanhos")
    plt.xlabel("Execuções")
    plt.ylabel("Tempo (ms)")
    plt.legend()
    plt.grid()
    plt.savefig("./Relatorios/tempos_execucao.png")
    plt.show()
    plt.close()

def main():
    times = util.read_files("bubbleSort")
    
    sizes = list(times.keys())
    
    # Valores estatísticos
    mean_times = [times[size][0].mean() for size in sizes]
    std_times = [times[size][0].std() for size in sizes]
    min_times = [times[size][0].min() for size in sizes]
    max_times = [times[size][0].max() for size in sizes]
    median_times = [times[size][0].median() for size in sizes]
    mode_times = [times[size][0].mode()[0] for size in sizes]

    # Criando DataFrame para os dados estatísticos
    stats_df = pd.DataFrame({
        "Tamanho": sizes,
        "Média": mean_times,
        "Desvio Padrão": std_times,
        "Mínimo": min_times,
        "Máximo": max_times,
        "Mediana": median_times,
        "Moda": mode_times
    })
    stats_df.to_csv("estatisticas_bubbleSort.csv", index=False, encoding="utf-16")
    print(stats_df)

    # Plotando histogramas
    for size in sizes:
        plot_histogram(times[size][0], size)

    # Plotando desvios padrão
    for size in sizes:
        plot_std(times[size][0], size)

    # Plotando tempos de execução
    plot_execution_time(times, sizes)

if __name__ == "__main__":
    main()