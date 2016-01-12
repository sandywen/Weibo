#this script aims for normalizing the features of the data after data cleaning
#!/usr/bin/python
import os
import sys
from numpy import *
reload(sys)
sys.setdefaultencoding('utf-8')

def norm(data_set):
    dataset_matrix = matrix(data_set)
    column_num = dataset_matrix.shape[1]
    for i in range(1, column_num): # first column is id
        column = dataset_matrix[:,i]
        maxval = column.max()
        minval = column.min()
        ranges = maxval - minval
        dataset_matrix[:,i] = 100 * (column - tile(minval, (column.shape[0], 1))) / tile(ranges, (column.shape[0], 1))

    return dataset_matrix
        

if __name__ == '__main__':
    ifile = open("result_201512042316")
    data_set = []
    for line in ifile:
        vec = line.strip().split("\t")
        digit_vec = [int(vec[0]), float(vec[1]), float(vec[2]), float(vec[3]), float(vec[4])]
        
        # average value of user_type
        user_type_vec = vec[5].strip(",").split(",")
        user_type_vec = [float(user_type_vec[i])*(i+1) for i in range(len(user_type_vec))]
        digit_vec.append(sum(user_type_vec)/len(user_type_vec))

        # variance of miroblogs
        blog_times = vec[6].strip(",").split(",")
        blog_times = [float(i) for i in blog_times]
        digit_vec.append(var(blog_times))

        digit_vec.append(float(vec[7]))
        digit_vec.append(float(vec[8]))
        digit_vec.append(float(vec[9]))
        digit_vec.append(float(vec[11])) # the tenth column is the same with 9th
        digit_vec.append(float(vec[12]))
        digit_vec.append(float(vec[13]))

        if vec[14] == "~":
            digit_vec.append(1.0) # post 1 blog in one day
        else:
            digit_vec.append((24*60)/float(vec[14]))

        data_set.append(digit_vec)

    normalized_set = norm(data_set).tolist()

    ifile.close()
    ofile = open("normalized_result.txt", "w")
    for i in range(len(normalized_set)):
        vec = [str(item) for item in normalized_set[i]]
        vec[0] = vec[0][:-2]
        ofile.write("\t".join(vec) + "\n")
    ofile.close()



