import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats
import pandas as pd
import util

def main():
    times = util.read_files("selectionSort")
    
    sizes = list(times.keys())
    
    # Valores estatísticos para o tempo de execução em milissegundos
    mean_times = [times[size][0].mean() for size in sizes]
    std_times = [times[size][0].std() for size in sizes]
    min_times = [times[size][0].min() for size in sizes]
    max_times = [times[size][0].max() for size in sizes]
    median_times = [times[size][0].median() for size in sizes]
    mode_times = [times[size][0].mode()[0] for size in sizes]

    # Valores estatísticos para o número de operações
    mean_ops = [times[size][1].mean() for size in sizes]
    std_ops = [times[size][1].std() for size in sizes]
    min_ops = [times[size][1].min() for size in sizes]
    max_ops = [times[size][1].max() for size in sizes]
    median_ops = [times[size][1].median() for size in sizes]
    mode_ops = [times[size][1].mode()[0] for size in sizes]

    # Criação do DataFrame com os resultados
    time_stats_df = pd.DataFrame({
        "Tamanho": sizes,
        "Tempo Médio (ms)": mean_times,
        "Desvio Padrão (ms)": std_times,
        "Mínimo (ms)": min_times,
        "Máximo (ms)": max_times,
        "Mediana (ms)": median_times,
        "Moda (ms)": mode_times,
    })

    ops_stats_df = pd.DataFrame({
        "Tamanho": sizes,
        "Operações Médias": mean_ops,
        "Desvio Padrão": std_ops,
        "Mínimo": min_ops,
        "Máximo": max_ops,
        "Mediana": median_ops,
        "Moda": mode_ops,
    })

    # Salvar os DataFrames em arquivos CSV
    time_stats_df.to_csv("selectionSort_time_stats.csv", index=False)
    ops_stats_df.to_csv("selectionSort_ops_stats.csv", index=False)

    plt.figure(figsize=(10, 6))
    plt.plot(sizes, mean_times, marker='o', label='Tempo Médio (ms)')
    plt.fill_between(sizes, np.array(mean_times) - np.array(std_times), np.array(mean_times) + np.array(std_times), alpha=0.2)
    plt.title('Selection Sort - Tempo Médio de Execução')
    plt.xlabel('Tamanho do Vetor')
    plt.ylabel('Tempo (ms)')
    plt.xticks(sizes, rotation=45)
    plt.ylim(0, max(mean_times) * 1.1)
    plt.grid()
    plt.legend()
    plt.savefig("selectionSort_time_stats.png")
    plt.show()

if __name__ == "__main__":
    main()