# this script is to do weighting k-means cluster
import numpy as np
from scipy.cluster import vq


data=np.loadtxt('./normalized_result.txt',delimiter='\t')
user_id=data[:,0]
vectors=data[:,1:]
weights=[1.25,1.25,1.25,1.0,1.5,1.5,1.0,1.0,1.0,1.5,1.5,1.0,1.0,]
centroids,distortion=vq.kmeans(vectors,2)

results1=(np.sum((vectors-centroids[0])**2,axis=1))**0.5
results2=(np.sum((vectors-centroids[1])**2,axis=1))**0.5

fout=open('./result.txt','w')
for i in range(len(user_id)):
    fout.write('%d\t'%user_id[i])
    if (results1[i]<results2[i]):
        fout.write('%d\t%.3f\t'%(0,1.00-(results1[i]-min(results1))/(max(results1)-min(results1))))
    else:
        fout.write('%d\t%.3f\t'%(1,1.00-(results2[i]-min(results2))/(max(results2)-min(results2))))
    for j in range(13):
        fout.write('%f\t'%float(vectors[i][j]))
    fout.write('\n')


