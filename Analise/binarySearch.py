import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats
import pandas as pd
import util

def main():
    times = util.read_files("binarySearch")
    
    sizes = list(times.keys())
    
    # Valores estatísticos para o tempo de execução em nanosegundos
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
        "Tempo Médio (ns)": mean_times,
        "Desvio Padrão (ns)": std_times,
        "Mínimo (ns)": min_times,
        "Máximo (ns)": max_times,
        "Mediana (ns)": median_times,
        "Moda (ns)": mode_times,
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
    time_stats_df.to_csv("binarySearch_time_stats.csv", index=False)
    ops_stats_df.to_csv("binarySearch_ops_stats.csv", index=False)

    plt.figure(figsize=(10, 6))
    plt.plot(sizes, mean_times, marker='o', label='Tempo Médio (ns)')
    plt.fill_between(sizes, np.array(mean_times) - np.array(std_times), np.array(mean_times) + np.array(std_times), alpha=0.2)
    plt.title('Binary Search - Tempo Médio de Execução')
    plt.xlabel('Tamanho do Vetor')
    plt.ylabel('Tempo (ns)')
    plt.xticks(sizes, rotation=45)
    plt.ylim(0, max(mean_times) * 1.1)
    plt.grid()
    plt.legend()
    plt.savefig("binarySearch_time_stats.png")
    plt.show()

if __name__ == "__main__":
    main()