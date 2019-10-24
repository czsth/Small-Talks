import os

def listFile(filename, formats):
    fileList = os.listdir(filename)
    leng = len(fileList)
    for i, f in enumerate(fileList):
        print(formats, end='')
        if i == leng - 1:
            print('└─', end='')
        else:
            print('├─', end='')
        print(f, end='')
        
        if os.path.isdir(os.path.join(filename, f)):
            print('/')
            
            if i == leng - 1:
                formats += '    '
            else:
                formats += '|   '
            listFile(os.path.join(filename, f), formats)
            formats = formats[:-4]
        else:
            print()

def tree(filename):
    if not os.path.exists(filename):
        return
    print(filename + '/')
    formats = ' '
    listFile(filename, formats)

tree(os.getcwd())
