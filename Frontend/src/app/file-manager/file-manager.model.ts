export interface FileEntity {
  id: number;
  fileName: string;
  fileType: string;
  size: number;
  uploadTime: string;
  expireTime?: string;
  data?: any;
}
