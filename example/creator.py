#!/usr/bin/python
import os


def clear_directory(dir_path):
    file_list = os.listdir(dir_path)
    for file_name in file_list:
        removed_file = "".join([dir_path,"/",file_name])
        os.remove(removed_file)



def main():
    clear_directory("./incoming")
    clear_directory("./outgoing")
    name_content_dict = {'Share_name' : 'GP123457', 'Ex_date' : '21082013',
                          'Rec_date' : '27082013', 'Dividend_amount_per_share' : '7'}
    dict = {'Trade_id': '7777777777', 'Seller_acct': '88888888', 'Buyer_acct': '99999999', 'Amount': 100,
            'TD': '01092014', 'SD': '01092014'}
    file_name = file_path_builder("./incoming",name_content_dict)
    # "./incoming/GP123456_21082013_27082013_7.csv"
    write_csv_file(file_name,dict)

def file_path_builder(dir_path,name_content_dict):
    file_name = "_".join([ name_content_dict['Share_name'],
                           name_content_dict['Ex_date'],
                           name_content_dict['Rec_date'],
                           name_content_dict['Dividend_amount_per_share']
    ])
    file_name = ".".join([file_name,"csv"])
    file_path = "/".join([dir_path,file_name])
    return file_path;


def write_csv_file(csv_file_path,values_dict):
    outfile = open(csv_file_path,"w")
    #writer = csv.writer(outfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_NONE)
    #writer.writerow(['Trade_id','Seller_acct','Buyer_acct','Amount','TD','SD'])
    #writer.writerow(['7777777777','88888888','99999999','100','01092014','01092014'])
    outfile.write(",".join(['Trade_id','Seller_acct','Buyer_acct','Amount','TD','SD']))
    outfile.write("\n");
    outfile.write(",".join(['7777777777','88888888','99999999','100','01092014','01092014']))
    #outfile.write("\n");
    outfile.close()
    #print csv.list_dialects()
    #with open(csv_file_path, 'wb') as csvfile:
    #    fieldnames = ['Trade_id','Seller_acct','Buyer_acct','Amount','TD','SD']
    #    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
    #    writer.writeheader()
    #    writer.writerow(values_dict)

if __name__ == "__main__":
    print "run creator.py"
    main()

