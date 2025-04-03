import pandas as pd

def read_files(prefix):
    sizes = {
        "100000": "30",
        "200000": "30",
        "400000": "30",
        "800000": "30"
    }
    times = {}

    for size, count in sizes.items():
        times[size] = pd.read_csv(f"./Data/{prefix}_{size}_{count}.csv", header=None, encoding="utf-16");

    return times