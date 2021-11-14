# minio-example
Minio client examples

## MacOs 下安装MinIO

```sh
# 下载
wget http://dl.minio.org.cn/server/minio/release/linux-amd64/minio

# 赋权
chmod +x minio

# 启动  /home/minio/data是自己定义的文件目录，进入minio的下载目录
mkdir /home/minio
mkdir /home/minio/data
mkdir /home/minio/log
cd /minio
./minio server /home/minio/data
nohup ./minio server /home/minio/data > /home/minio/log/minio.log 2>&1 &

# api http://127.0.0.1:9000
# console: http://127.0.0.1:36085
 
# 静默启动 
# nohup ./minio server --address 0.0.0.0:8000 /home/minio/data > /home/minio/log/minio.log 2>&1 &

# http://ipaddress:8000/minio/login  
```